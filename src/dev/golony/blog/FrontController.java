package dev.golony.blog;

import org.apache.axis.session.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FrontController(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        System.out.println("GET Method Requested");
        System.out.println("--------------------------");
        this.actionDo(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        System.out.println("POST Method Requested");
        System.out.println("--------------------------");
        this.actionDo(req, resp);
    }

    private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("actionDo Called");

        String url = request.getRequestURI();
        String conPath = request.getContextPath();
        String command = url.substring(conPath.length());
        String template_path = null;

        System.out.printf("url: %s \t conPath: %s \t command: %s \n", url, conPath, command);


        if (command.matches("/user")){
            System.out.println("user 관련 요청");
        }
        else if (command.matches("/post")){
            System.out.println("게시글 관련 요청");
        }
        else{
            System.out.println("매칭된 패턴 없음");
        }

        if (command.equals("/index.do")){
            System.out.println("index.do Matched");

            HttpSession session = request.getSession(true);
            System.out.println(String.valueOf(session.getAttribute("logged_in")));

            if (session.getAttribute("logged_in") == null){
                response.sendRedirect("/login.do");
                return;
            }
            else{
                template_path = "template/common/index.jsp";
            }
        }
        else if (command.equals("/login.do")){
            System.out.println("login.do Matched");
            template_path = "template/common/login.jsp";
        }
        else if (command.equals("/trylogin.do")){
            System.out.println("login procedure init");
            MemberService service = MemberService.getInstance();

            boolean isSuccess = service.login(request.getParameter("id"),
                    request.getParameter("pw"));

            if (isSuccess == true){
                HttpSession sess = request.getSession();
                sess.setAttribute("logged_in", true);
                sess.setAttribute("id", request.getParameter("id"));
                response.sendRedirect("/index.do");
                return;
            }
            else {
                request.setAttribute("msg", "올바르지 않은 계정정보입니다.");
                template_path = "template/common/error.jsp";
            }
        }
        else if (command.equals("/logout.do")){
            System.out.println("Logout Event");
            HttpSession sess = request.getSession();
            sess.removeAttribute("logged_in");
            sess.removeAttribute("id");
            response.sendRedirect("/login.do");
            return;
        }
        else if (command.equals("/user/join.do")){
            System.out.println("Join Page Requested");
            // '/'를 빼면 상대경로로서, .do가 붙은 마지막 부분만 변경되어 jsp 파일을 찾지 못함
            template_path = "/template/user/join.jsp";
        }
        else if (command.equals("/user/register.do")){
            MemberService service = MemberService.getInstance();
            System.out.printf("회원가입 시도: Name: %s \t ID: %s \t PW: %s\n",
                    (String)request.getParameter("name"),
                    (String)request.getParameter("id"),
                    (String)request.getParameter("pw"));

            boolean isSuccess = service.join((String)request.getParameter("name"),
                    (String)request.getParameter("id"),
                    (String)request.getParameter("pw"));

            if (isSuccess){
                HttpSession sess = request.getSession();
                sess.setAttribute("logged_in", true);
                sess.setAttribute("id", (String) request.getParameter("id"));
                response.sendRedirect("/index.do");
                return;
            }
            else{
                template_path = "common/error.jsp";
                request.setAttribute("msg", "회원가입에 실패했습니다. 다시시도해주세요.");
            }
        }

        else if (command.equals("/post/list.do")){
            System.out.println("list.do Matched");
            PostService service = new PostService();

            ArrayList<PostDto> values = service.getList();
            request.setAttribute("list", values);
            template_path = "/template/post/list.jsp";
        }

        else if (command.equals("/post/content.do")){
            System.out.printf("content.do Matched id=%s\n", request.getParameter("id"));
            int id = Integer.parseInt(request.getParameter("id"));

            PostService service = new PostService();
            PostDto post = service.getPost(id);     // TODO: 조회수 증가
            request.setAttribute("post", post);
            template_path = "/template/post/content.jsp";

        }

        else if (command.equals("/post/write.do")){
            String method = request.getMethod();
            System.out.printf("write.do Marched method: %s\n", method);

            if (method.equals("GET")){
                HttpSession sess = request.getSession();
                if (sess.getAttribute("id") == null){
                    response.sendRedirect("/login.do");
                    return;
                }

                template_path = "/template/post/post_form.jsp";
            }
            else if (method.equals("POST")){
                PostService service = new PostService();
                HttpSession sess = request.getSession();

                String title = (String) request.getParameter("title");
                String content = (String) request.getParameter("content");
                String name = (String) sess.getAttribute("id");
//                String name = "temp";
                SimpleDateFormat fomatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = fomatter.format(new Date());
                System.out.println(date);

                service.registerNewPost(name, title, content, date);

                response.sendRedirect("/post/list.do");
                return;
            }
        }

        else if (command.equals("/post/edit.do")) {
            if (request.getMethod().equals("GET")) {
                System.out.println("Edit Request Occurred");

                PostService service = new PostService();
                // TODO: 권한확인
                int id = Integer.parseInt(request.getParameter("id"));
                PostDto data = service.getPost(id);
                request.setAttribute("dto", data);

                // 세션을 이용한 수정 대상 관리
                HttpSession sess = request.getSession();
                template_path = "/template/post/post_form.jsp";

                String sessId = (String) sess.getAttribute("id");
                String dtoName = data.getName();

                System.out.printf("name: %s id: %s\n", sessId, dtoName);

                // TODO: 왜 안되는 걸까?
                if (sessId.equals(dtoName)){
                    System.out.println("권한 오류 발생");
                    request.setAttribute("msg", "권한이 없습니다.");
                    template_path = "/template/common/error.jsp";
                }

                sess.setAttribute("bId", data.getbId());
            }
            else if (request.getMethod().equals("POST")){
                System.out.println("Revised Post Arrived");

                PostService service = new PostService();
                HttpSession sess = request.getSession();

                String title = request.getParameter("title");
                String content = request.getParameter("content");
                int bId = Integer.parseInt(request.getParameter("bId"));

                if (sess.getAttribute("id") == null){
                    response.sendRedirect("/login.do");
                    return;
                }

                service.edit(bId, (String) sess.getAttribute("id"),
                        title, content);

                response.sendRedirect(String.format("/post/content.do?id=%d", bId));
                return;
            }
        }
        else if (command.equals("/post/delete.do")){
            if (request.getMethod().equals("POST")){
               PostService service = new PostService();

               int bId = Integer.parseInt(request.getParameter("id"));

               service.delete(bId);
               response.sendRedirect("/post/list.do");
               return;
            }
        }

        System.out.printf("template_path: %s\n", template_path);
        System.out.println("-------------------------------------------");

        RequestDispatcher dispatcher = request.getRequestDispatcher(template_path);
        dispatcher.forward(request, response);
//        dispatcher.include(request, response);
    }

    // TODO: HTTP Method 분리
    private void actionPost(HttpServletRequest req, HttpServletResponse resp){

    }

    private void actionMember(HttpServletRequest req, HttpServletResponse resp){

    }
}

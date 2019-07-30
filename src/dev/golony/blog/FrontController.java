package dev.golony.blog;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
            template_path = "template/post/list.jsp";
        }

        else if (command.equals("/post/write.do")){

        }

        System.out.println("-------------------------------------------");

        RequestDispatcher dispatcher = request.getRequestDispatcher(template_path);
        dispatcher.forward(request, response);
//        dispatcher.include(request, response);
    }
}

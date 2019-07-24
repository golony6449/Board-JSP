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
            System.out.println((String)session.getAttribute("logged_in"));

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
            boolean isSuccess = false;

            if (isSuccess == true){
                template_path = "template/common/index.jsp";
            }
            else {
                request.setAttribute("msg", "올바르지 않은 계정정보입니다.");
                template_path = "template/common/error.jsp";
            }
        }
        else if (command.equals("/post/list.do")){
            System.out.println("list.do Matched");
            template_path = "template/post/list.jsp";

        }

        System.out.println("-------------------------------------------");

        RequestDispatcher dispatcher = request.getRequestDispatcher(template_path);
        dispatcher.forward(request, response);
//        dispatcher.include(request, response);
    }
}

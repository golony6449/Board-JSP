package dev.golony.blog;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            template_path = "template/common/index.jsp";
        }
        else if (command.equals("/login.do")){
            System.out.println("login.do Matched");
            template_path = "template/common/login.jsp";
        }

        System.out.println("-------------------------------------------");

        RequestDispatcher dispatcher = request.getRequestDispatcher(template_path);
        dispatcher.forward(request, response);
//        dispatcher.include(request, response);
    }
}

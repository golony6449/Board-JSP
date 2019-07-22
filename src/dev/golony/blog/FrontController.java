package dev.golony.blog;

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
        super.doGet(req, resp);
        System.out.println("GET Method Requested");
        System.out.println("--------------------------");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        System.out.println("POST Method Requested");
        System.out.println("--------------------------");
        this.actionDo(req, resp);

    }

    private void actionDo(HttpServletRequest request, HttpServletResponse response){
        String url = request.getRequestURI();
        String conPath = request.getContextPath();
        String command = url.substring(conPath.length());

        System.out.printf("url: %s \t conPath: %s \t command: %s \n", url, conPath, command);
    }
}

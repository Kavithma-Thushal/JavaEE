package lk.ijse.gdse66.pos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : Kavithma Thushal
 * @project : JavaEE
 * @since : 7:38 AM - 1/6/2024
 **/
@WebServlet("/customers")
public class customers extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("doGet()");
        System.out.println("doGet()");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("doPost()");
        System.out.println("doPost()");
    }
}
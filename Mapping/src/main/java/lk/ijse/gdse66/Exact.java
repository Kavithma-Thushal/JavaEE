package lk.ijse.gdse66;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : Kavithma Thushal
 * @project : JavaEE
 * @since : 1:01 AM - 1/5/2024
 **/
@WebServlet(urlPatterns = "/test")
public class Exact extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<h1>Exact Mapping</h1>");
    }
}
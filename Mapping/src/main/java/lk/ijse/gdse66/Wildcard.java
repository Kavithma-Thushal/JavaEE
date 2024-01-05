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
 * @since : 1:03 AM - 1/5/2024
 **/
@WebServlet(urlPatterns = "/hello/*")
public class Wildcard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<h1>Wildcard Mapping</h1>");

        /*http://localhost:8080/map/hello/test?id=C001&name=Thushal&address=Galle*/

        System.out.println("-------------------- Wildcard Mapping --------------------");
        System.out.println("Context Path    : " + req.getContextPath());
        System.out.println("Path Info       : " + req.getPathInfo());
        System.out.println("Servlet Path    : " + req.getServletPath());
        System.out.println("Path translated : " + req.getPathTranslated());
        System.out.println("Query String    : " + req.getQueryString());
        System.out.println("Request URI     : " + req.getRequestURI());
        System.out.println("Request URL     : " + req.getRequestURL());
    }
}
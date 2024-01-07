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

        /*http://localhost:8080/map/test?id=C001&name=Thushal&address=Galle*/

        System.out.println("-------------------- Exact Mapping --------------------");
        System.out.println("Context Path    : " + req.getContextPath());
        System.out.println("Path Info       : " + req.getPathInfo());
        System.out.println("Servlet Path    : " + req.getServletPath());
        System.out.println("Path translated : " + req.getPathTranslated());
        System.out.println("Query String    : " + req.getQueryString());
        System.out.println("Request URI     : " + req.getRequestURI());
        System.out.println("Request URL     : " + req.getRequestURL());

        System.out.println("protocol        : " + req.getProtocol());
        System.out.println("Scheme          : " + req.getScheme());
        System.out.println("Remote Address  : " + req.getRemoteAddr());
        System.out.println("Request Port    : " + req.getRemotePort());
        System.out.println("Remote Host     : " + req.getRemoteHost());
        System.out.println("Server Name     : " + req.getServerName());
        System.out.println("Server Port     : " + req.getServerPort());
        System.out.println("Method          : " + req.getMethod());
    }
}
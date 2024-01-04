package lk.ijse.gdse66;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : Kavithma Thushal
 * @project : JavaEE
 * @since : 12:10 AM - 1/5/2024
 **/
public class MyServlet extends HttpServlet {

    static {
        System.out.println("Servlet is being Initialized! - 1");
    }

    public MyServlet() {
        System.out.println("constructor() - 1");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init(parameter) - 1");
        super.init(config);
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init() - 1");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("MyServlet 1");
        System.out.println("service methods() - 1");
    }

    @Override
    public void destroy() {
        System.out.println("Servlet is Destroyed! - 1");
    }
}
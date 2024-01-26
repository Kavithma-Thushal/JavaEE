package lk.ijse.gdse66;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author : Kavithma Thushal
 * @project : JavaEE
 * @since : 6:24 PM - 1/26/2024
 **/
@WebServlet(urlPatterns = "/test")
public class MyServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        try {
            InitialContext ic = new InitialContext();
            DataSource pool = (DataSource) ic.lookup("java:/comp/env/jdbc/pos");

            Connection connection = pool.getConnection();
            System.out.println(connection);
        } catch (NamingException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Thi is JNDI");
        System.out.println("Thi is JNDI");
    }
}
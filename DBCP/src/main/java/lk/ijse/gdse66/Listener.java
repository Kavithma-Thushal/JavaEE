package lk.ijse.gdse66;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author : Kavithma Thushal
 * @project : JavaEE
 * @since : 1:56 PM - 1/20/2024
 **/
@WebListener
public class Listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Servlet is being Initialized...!");

        BasicDataSource dbcp = new BasicDataSource();        // create a connection pool
        dbcp.setUsername("root");
        dbcp.setPassword("1234");
        dbcp.setUrl("jdbc:mysql://localhost:3306/servlet_db");
        dbcp.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dbcp.setInitialSize(5);
        dbcp.setMaxTotal(10);

        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("dbcp", dbcp);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Connection Pool Destroyed...!");
    }
}
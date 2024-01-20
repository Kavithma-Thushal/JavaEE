package lk.ijse.gdse66.Task.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : Kavithma Thushal
 * @project : JavaEE
 * @since : 11:57 AM - 1/20/2024
 **/
/*@WebFilter(urlPatterns = {"/customer", "/item"})*/
public class Filter2 extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("Filter2 : Incoming Request");
        chain.doFilter(req, res);
        System.out.println("Filter2 : Outgoing Response");
    }
}
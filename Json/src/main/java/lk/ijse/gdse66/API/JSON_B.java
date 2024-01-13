package lk.ijse.gdse66.API;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.gdse66.Model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author : Kavithma Thushal
 * @project : JavaEE
 * @since : 4:04 PM - 1/13/2024
 **/
@WebServlet(urlPatterns = "/jsonb")
public class JSON_B extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /**JSON Object ---> Java Object*/
        Jsonb jsonb = JsonbBuilder.create();

        Customer customer = jsonb.fromJson(req.getReader(), Customer.class);
        System.out.println(customer);

        ArrayList<Customer> customerList = jsonb.fromJson(req.getReader(), new ArrayList<Customer>() {
        }.getClass().getGenericSuperclass());
        System.out.println(customerList);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /**Java Object ---> JSON Object*/
        Customer s1 = new Customer("C001", "Thushal", "Galle");
        Customer s2 = new Customer("C002", "Kamal", "Kandy");

        ArrayList<Customer> arrayList = new ArrayList<>();
        arrayList.add(s1);
        arrayList.add(s2);

        /*Jsonb jsonb = JsonbBuilder.create();
        String json = jsonb.toJson(arrayList);
        resp.getWriter().write(json);*/

        Jsonb jsonb = JsonbBuilder.create();
        jsonb.toJson(arrayList, resp.getWriter());
    }
}
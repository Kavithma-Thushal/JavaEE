package lk.ijse.gdse66.pos;

import jakarta.json.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * @author : Kavithma Thushal
 * @project : JavaEE
 * @since : 7:38 AM - 1/6/2024
 **/
@WebServlet("/customers")
public class customers extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String address = req.getParameter("address");

        /*Using JSON Object*/
        /*JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        String address = jsonObject.getString("address");*/

        System.out.println("doPost()");
        System.out.println(id + " - " + name + " - " + address);

        resp.getWriter().println("doPost()");
        resp.getWriter().println(id + " - " + name + " - " + address);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_db", "root", "1234");

            String sql = "INSERT INTO customer(id, name, address) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, address);
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet()");
        resp.getWriter().println("doGet()");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_db", "root", "1234");

            String sql = "SELECT * FROM customer";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");

                System.out.println(id + " - " + name + " - " + address);
                resp.getWriter().println(id + " - " + name + " - " + address);
            }

            /*Using JSON Object*/
            /*JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");

                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("id", id);
                objectBuilder.add("name", name);
                objectBuilder.add("address", address);
                JsonObject customerObject = objectBuilder.build();      // Create JSON objects for each customer
                arrayBuilder.add(customerObject);                       // Add each customer into JSON array
            }

            JsonArray customerArray = arrayBuilder.build();
            resp.getWriter().println(customerArray.toString());         // Write JSON array in response
            resp.setContentType("application/json");        // Set the MIME type of the content of the response (Thus, add response header called "Content-Type")*/

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
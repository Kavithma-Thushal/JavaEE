package lk.ijse.gdse66.pos.Servlet;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.gdse66.pos.DTO.CustomerDTO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
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
@WebServlet(urlPatterns = "/customers", loadOnStartup = 1, initParams = {
        @WebInitParam(name = "username", value = "root"),
        @WebInitParam(name = "password", value = "1234"),
        @WebInitParam(name = "url", value = "jdbc:mysql://localhost:3306/servlet_db")
})
public class CustomerServlet extends HttpServlet {
    private String username;
    private String password;
    private String url;

    @Override
    public void init() throws ServletException {
        /*ServletConfig is used to get configuration information such as database username, password and url*/
        ServletConfig servletConfig = getServletConfig();
        username = servletConfig.getInitParameter("username");
        password = servletConfig.getInitParameter("password");
        url = servletConfig.getInitParameter("url");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;

        /*Using Query Parameters*/
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String address = req.getParameter("address");

        /*Using JSON-P Object*/
        /*JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        String address = jsonObject.getString("address");*/

        /*Using Json-B Object*/
        /*Jsonb jsonb = JsonbBuilder.create();
        CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);   // JSON Object ---> Java Object
        String id = customerDTO.getId();
        String name = customerDTO.getName();
        String address = customerDTO.getAddress();*/

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);

            String sql = "INSERT INTO customer(id, name, address) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, address);
            int rowsEffected = preparedStatement.executeUpdate();

            if (rowsEffected != 0) {
                resp.getWriter().println("Customer Saved Successfully!");
                System.out.println("Customer Saved Successfully!");
            } else {
                resp.getWriter().println("Customer Saved Error!");
                System.out.println("Customer Saved Error!");
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);

            String sql = "SELECT * FROM customer";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            /*Using Query Parameters*/
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");

                System.out.println(id + " - " + name + " - " + address);
                resp.getWriter().println(id + " - " + name + " - " + address);
            }

            /*Using JSON-P*/
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
                arrayBuilder.add(customerObject);                       // Add each Customer Object into JSON array
            }

            JsonArray customerArray = arrayBuilder.build();
            resp.getWriter().println(customerArray.toString());         // Write JSON array in response
            resp.setContentType("application/json");                    // Set the MIME type of the content of the response*/

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;

        /*Using Query Parameters*/
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String address = req.getParameter("address");

        /*Using JSON-P Object*/
        /*JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        String address = jsonObject.getString("address");*/

        /*Using Json-B Object*/
        /*Jsonb jsonb = JsonbBuilder.create();
        CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);   // JSON Object ---> Java Object
        String id = customerDTO.getId();
        String name = customerDTO.getName();
        String address = customerDTO.getAddress();*/

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);

            String sql = "UPDATE customer SET name=?, address=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, id);
            int rowsEffected = preparedStatement.executeUpdate();

            if (rowsEffected != 0) {
                resp.getWriter().println("Customer Updated Successfully!");
                System.out.println("Customer Updated Successfully!");
            } else {
                resp.getWriter().println("Customer Updated Error!");
                System.out.println("Customer Updated Error!");
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;

        String id = req.getParameter("id");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);

            String sql = "DELETE FROM customer WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            int rowsEffected = preparedStatement.executeUpdate();

            if (rowsEffected != 0) {
                resp.getWriter().println("Customer Deleted Successfully!");
                System.out.println("Customer Deleted Successfully!");
            } else {
                resp.getWriter().println("Customer Deleted Error!");
                System.out.println("Customer Deleted Error!");
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
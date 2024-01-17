package lk.ijse.gdse66.pos.api;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.gdse66.pos.dto.CustomerDTO;

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
@WebServlet(urlPatterns = "/customer", loadOnStartup = 1, initParams = {@WebInitParam(name = "username", value = "root"), @WebInitParam(name = "password", value = "1234"), @WebInitParam(name = "url", value = "jdbc:mysql://localhost:3306/javaee_customers")})
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
        double salary = Double.parseDouble(req.getParameter("salary"));

        /*Using JSON-P Object*/
        /*JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        String address = jsonObject.getString("address");
        double salary = jsonObject.getJsonNumber("salary").doubleValue();*/

        /*Using Json-B Object*/
        /*Jsonb jsonb = JsonbBuilder.create();
        CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);   // JSON Object ---> Java Object
        String id = customerDTO.getId();
        String name = customerDTO.getName();
        String address = customerDTO.getAddress();
        double salary = customerDTO.getSalary();*/

        /*Validations*/
        if (id == null || !id.matches("C\\d{3}")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID is Invalid!");
            resp.getWriter().println("ID is Invalid!");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);

            String sql = "INSERT INTO customer(id, name, address, salary) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, address);
            preparedStatement.setDouble(4, salary);
            int rowsEffected = preparedStatement.executeUpdate();

            if (rowsEffected != 0) {
                //resp.setStatus(204);
                //resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                resp.getWriter().println("Customer Saved Successfully!");
                System.out.println("Customer Saved Successfully!");
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Customer Saved Error!");
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

        /*String action = req.getParameter("action");
        if (action == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action is Empty!");
        } else if (action.equalsIgnoreCase("GETALL")) {
            getAllCustomers();
        } else if (action.equalsIgnoreCase("GETONE")) {
            getCustomerById();
        }*/

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
            resp.getWriter().println(customerArray.toString());
            System.out.println(customerArray);
            resp.setContentType("application/json");                    // Set the MIME type of the content of the response*/

            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            /*Using JSON-B*/
            /*ArrayList<CustomerDTO> customerList = new ArrayList<>();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");

                customerList.add(new CustomerDTO(id, name, address));
            }

            Jsonb jsonb = JsonbBuilder.create();
            resp.setContentType("application/json");                    // Set the MIME type of the content of the response
            jsonb.toJson(customerList, resp.getWriter());               // Java Object ---> JSON Object
            jsonb.toJson(customerList, System.out);*/

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
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Customer Updated Error!");
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
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Customer Deleted Error!");
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

    private void getAllCustomers() {
        System.out.println("This is Get All");
    }

    private void getCustomerById() {
        System.out.println("This is Get One");
    }
}
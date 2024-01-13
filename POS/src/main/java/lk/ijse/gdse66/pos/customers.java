package lk.ijse.gdse66.pos;

import jakarta.json.*;

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
public class customers extends HttpServlet {
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
            Connection connection = DriverManager.getConnection(url, username, password);

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
            Connection connection = DriverManager.getConnection(url, username, password);

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
                arrayBuilder.add(customerObject);                       // Add each Customer Object into JSON array
            }

            JsonArray customerArray = arrayBuilder.build();
            resp.getWriter().println(customerArray.toString());         // Write JSON array in response
            resp.setContentType("application/json");                    // Set the MIME type of the content of the response*/

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String address = req.getParameter("address");

        /*Using JSON Object*/
        /*JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        String address = jsonObject.getString("address");*/

        System.out.println("doPut()");
        System.out.println(id + " - " + name + " - " + address);

        resp.getWriter().println("doPut()");
        resp.getWriter().println(id + " - " + name + " - " + address);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            String sql = "UPDATE customer SET name=?, address=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, id);
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
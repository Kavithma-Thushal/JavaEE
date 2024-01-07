package lk.ijse.gdse66;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author : Kavithma Thushal
 * @project : JavaEE
 * @since : 7:11 PM - 1/5/2024
 **/
@WebServlet(urlPatterns = "/test")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*-------------------- JSON Read --------------------*/
        /*Without Json Library*/
        BufferedReader reader1 = req.getReader();
        String line;
        while ((line = reader1.readLine()) != null) {
            System.out.println(line);
        }

        /*Using JSON-P Library*/
        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        System.out.println(jsonObject);

        /*-------------------- JSON Write --------------------*/
        /*Using JSON-P Library*/
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("id", "C001");
        objectBuilder.add("name", "Thushal");
        objectBuilder.add("address", "Galle");
        JsonObject customerObject = objectBuilder.build();
        resp.getWriter().println(customerObject.toString());
    }
}
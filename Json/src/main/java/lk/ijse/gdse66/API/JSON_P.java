package lk.ijse.gdse66.API;

import jakarta.json.*;

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
@WebServlet(urlPatterns = "/jsonp")
public class JSON_P extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /**Without Json Library*/
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        /**Using JSON-P Library*/
        JsonReader jsonpReader = Json.createReader(req.getReader());
        JsonObject jsonpObject = jsonpReader.readObject();
        System.out.println(jsonpObject);

        /**Using JSON-P Library Advanced*/
        JsonReader jsonbReader = Json.createReader(req.getReader());
        JsonObject jsonbObject = jsonbReader.readObject();

        String id = jsonbObject.getString("id");
        String name = jsonbObject.getString("name");
        JsonObject addressJsonObject = jsonbObject.getJsonObject("address");

        int no = addressJsonObject.getInt("no");
        String street = addressJsonObject.getString("street");
        String city = addressJsonObject.getString("city");

        JsonArray contactsJsonArray = jsonbObject.getJsonArray("contacts");
        String firstContact = contactsJsonArray.getString(0);
        String secondContact = contactsJsonArray.getString(1);

        System.out.println("id : " + id);
        System.out.println("name : " + name);

        System.out.println("no : " + no);
        System.out.println("street : " + street);
        System.out.println("city : " + city);

        System.out.println("firstContact : " + firstContact);
        System.out.println("secondContact : " + secondContact);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /**Without Json Library*/
        resp.getWriter().write("{" +
                "\"id\":\"C001\"," +
                "\"name\":\"Nimal\"," +
                "\"address\":\"Jaffna\"," +
                "}");

        /**Using JSON-P Library*/
        JsonObjectBuilder jsonpObjectBuilder = Json.createObjectBuilder();
        jsonpObjectBuilder.add("id", "C001");
        jsonpObjectBuilder.add("name", "Thushal");
        jsonpObjectBuilder.add("address", "Galle");
        JsonObject customerObject = jsonpObjectBuilder.build();
        resp.getWriter().println(customerObject.toString());

        /**Using JSON-P Library Advanced*/
        JsonObjectBuilder jsonbObjectBuilder = Json.createObjectBuilder();
        jsonbObjectBuilder.add("id", "C001");
        jsonbObjectBuilder.add("name", "Thushal");

        JsonObjectBuilder addressObject = Json.createObjectBuilder();
        addressObject.add("no", 168);
        addressObject.add("street", "Watareka Road");
        addressObject.add("city", "Galle");
        jsonbObjectBuilder.add("address", addressObject);

        JsonArrayBuilder contactsArrayBuilder = Json.createArrayBuilder();
        contactsArrayBuilder.add("0774519007");
        contactsArrayBuilder.add("0777834633");

        jsonbObjectBuilder.add("contacts", contactsArrayBuilder);
        JsonObject jsonObject = jsonbObjectBuilder.build();
        resp.getWriter().write(jsonObject.toString());
    }
}
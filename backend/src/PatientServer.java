import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PatientServer {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(
                new InetSocketAddress(8080),
                0
        );

        server.createContext("/patients", PatientServer::getPatients);

        server.setExecutor(null);

        System.out.println("Patient server started!");
        System.out.println("Open: http://localhost:8080/patients");

        server.start();
    }

    public static void getPatients(HttpExchange exchange) throws IOException {

        try {

            Connection connection = DatabaseConnection.getConnection();

            String sql = "SELECT * FROM patients";

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            StringBuilder json = new StringBuilder();

            json.append("[");

            boolean first = true;

            while (result.next()) {

                if (!first) {
                    json.append(",");
                }

                json.append("{");

                json.append("\"patient_id\":")
                    .append(result.getInt("patient_id"))
                    .append(",");

                json.append("\"name\":\"")
                    .append(result.getString("name"))
                    .append("\",");

                json.append("\"age\":")
                    .append(result.getInt("age"))
                    .append(",");

                json.append("\"gender\":\"")
                    .append(result.getString("gender"))
                    .append("\",");

                json.append("\"phone\":\"")
                    .append(result.getString("phone"))
                    .append("\",");

                json.append("\"blood_group\":\"")
                    .append(result.getString("blood_group"))
                    .append("\"");

                json.append("}");

                first = false;
            }

            json.append("]");

            String response = json.toString();

            exchange.getResponseHeaders()
                    .set("Content-Type", "application/json");

            exchange.getResponseHeaders()
                    .set("Access-Control-Allow-Origin", "*");

            exchange.sendResponseHeaders(
                    200,
                    response.getBytes().length
            );

            OutputStream output = exchange.getResponseBody();

            output.write(response.getBytes());

            output.close();

            result.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            e.printStackTrace();

            String response = "{\"error\":\"Failed to fetch patients\"}";

            exchange.sendResponseHeaders(
                    500,
                    response.getBytes().length
            );

            OutputStream output = exchange.getResponseBody();

            output.write(response.getBytes());

            output.close();
        }
    }
}
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
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

        server.createContext(
                "/patients",
                PatientServer::handlePatients
        );

        server.setExecutor(null);

        System.out.println("Patient server started!");
        System.out.println(
                "Open: http://localhost:8080/patients"
        );

        server.start();
    }


    // ==========================================
    // HANDLE GET, POST AND OPTIONS REQUESTS
    // ==========================================

    public static void handlePatients(
            HttpExchange exchange) throws IOException {

        String method =
                exchange.getRequestMethod();


        // CORS preflight request
        if (method.equalsIgnoreCase("OPTIONS")) {

            exchange.getResponseHeaders()
                    .set(
                            "Access-Control-Allow-Origin",
                            "*"
                    );

            exchange.getResponseHeaders()
                    .set(
                            "Access-Control-Allow-Methods",
                            "GET, POST, OPTIONS"
                    );

            exchange.getResponseHeaders()
                    .set(
                            "Access-Control-Allow-Headers",
                            "Content-Type"
                    );

            exchange.sendResponseHeaders(
                    204,
                    -1
            );

            exchange.close();

            return;
        }


        // GET request
        if (method.equalsIgnoreCase("GET")) {

            String path =
                    exchange.getRequestURI().getPath();


            // Get all patients
            if (path.equals("/patients")) {

                getPatients(exchange);

            }

            // Get patient by ID
            else {

                getPatientById(exchange);
            }
        }


        // POST request
        else if (method.equalsIgnoreCase("POST")) {

            addPatient(exchange);
        }


        // Other methods
        else {

            String response =
                    "{\"error\":\"Method not allowed\"}";

            exchange.sendResponseHeaders(
                    405,
                    response.getBytes().length
            );

            OutputStream output =
                    exchange.getResponseBody();

            output.write(response.getBytes());

            output.close();
        }
    }


    // ==========================================
    // GET - FETCH ALL PATIENTS
    // ==========================================

    public static void getPatients(
            HttpExchange exchange) throws IOException {

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            String sql =
                    "SELECT * FROM patients";

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet result =
                    statement.executeQuery();


            StringBuilder json =
                    new StringBuilder();

            json.append("[");

            boolean first = true;


            while (result.next()) {

                if (!first) {

                    json.append(",");
                }


                json.append("{");


                json.append("\"patient_id\":")
                        .append(
                                result.getInt(
                                        "patient_id"
                                )
                        )
                        .append(",");


                json.append("\"name\":\"")
                        .append(
                                result.getString(
                                        "name"
                                )
                        )
                        .append("\",");


                json.append("\"age\":")
                        .append(
                                result.getInt(
                                        "age"
                                )
                        )
                        .append(",");


                json.append("\"gender\":\"")
                        .append(
                                result.getString(
                                        "gender"
                                )
                        )
                        .append("\",");


                json.append("\"phone\":\"")
                        .append(
                                result.getString(
                                        "phone"
                                )
                        )
                        .append("\",");


                json.append("\"blood_group\":\"")
                        .append(
                                result.getString(
                                        "blood_group"
                                )
                        )
                        .append("\"");


                json.append("}");


                first = false;
            }


            json.append("]");


            String response =
                    json.toString();


            exchange.getResponseHeaders()
                    .set(
                            "Content-Type",
                            "application/json"
                    );

            exchange.getResponseHeaders()
                    .set(
                            "Access-Control-Allow-Origin",
                            "*"
                    );


            exchange.sendResponseHeaders(
                    200,
                    response.getBytes().length
            );


            OutputStream output =
                    exchange.getResponseBody();

            output.write(
                    response.getBytes()
            );

            output.close();


            result.close();
            statement.close();
            connection.close();


        } catch (Exception e) {

            e.printStackTrace();


            String response =
                    "{\"error\":\"Failed to fetch patients\"}";


            exchange.sendResponseHeaders(
                    500,
                    response.getBytes().length
            );


            OutputStream output =
                    exchange.getResponseBody();

            output.write(
                    response.getBytes()
            );

            output.close();
        }
    }


    // ==========================================
    // GET - FETCH ONE PATIENT BY ID
    // ==========================================

    public static void getPatientById(
            HttpExchange exchange)
            throws IOException {

        try {

            String path =
                    exchange.getRequestURI().getPath();


            String idString =
                    path.substring(
                            "/patients/".length()
                    );


            int patientId =
                    Integer.parseInt(idString);


            Connection connection =
                    DatabaseConnection.getConnection();


            String sql =
                    "SELECT * FROM patients " +
                    "WHERE patient_id = ?";


            PreparedStatement statement =
                    connection.prepareStatement(sql);


            statement.setInt(
                    1,
                    patientId
            );


            ResultSet result =
                    statement.executeQuery();


            if (result.next()) {

                StringBuilder json =
                        new StringBuilder();


                json.append("{");


                json.append("\"patient_id\":")
                        .append(
                                result.getInt(
                                        "patient_id"
                                )
                        )
                        .append(",");


                json.append("\"name\":\"")
                        .append(
                                result.getString(
                                        "name"
                                )
                        )
                        .append("\",");


                json.append("\"age\":")
                        .append(
                                result.getInt(
                                        "age"
                                )
                        )
                        .append(",");


                json.append("\"gender\":\"")
                        .append(
                                result.getString(
                                        "gender"
                                )
                        )
                        .append("\",");


                json.append("\"phone\":\"")
                        .append(
                                result.getString(
                                        "phone"
                                )
                        )
                        .append("\",");


                json.append("\"email\":\"")
                        .append(
                                result.getString(
                                        "email"
                                )
                        )
                        .append("\",");


                json.append("\"address\":\"")
                        .append(
                                result.getString(
                                        "address"
                                )
                        )
                        .append("\",");


                json.append("\"blood_group\":\"")
                        .append(
                                result.getString(
                                        "blood_group"
                                )
                        )
                        .append("\"");


                json.append("}");


                String response =
                        json.toString();


                exchange.getResponseHeaders()
                        .set(
                                "Content-Type",
                                "application/json"
                        );


                exchange.getResponseHeaders()
                        .set(
                                "Access-Control-Allow-Origin",
                                "*"
                        );


                exchange.sendResponseHeaders(
                        200,
                        response.getBytes().length
                );


                OutputStream output =
                        exchange.getResponseBody();


                output.write(
                        response.getBytes()
                );


                output.close();

            }

            else {

                String response =
                        "{\"error\":\"Patient not found\"}";


                exchange.getResponseHeaders()
                        .set(
                                "Access-Control-Allow-Origin",
                                "*"
                        );


                exchange.sendResponseHeaders(
                        404,
                        response.getBytes().length
                );


                OutputStream output =
                        exchange.getResponseBody();


                output.write(
                        response.getBytes()
                );


                output.close();
            }


            result.close();
            statement.close();
            connection.close();


        } catch (Exception e) {

            e.printStackTrace();


            String response =
                    "{\"error\":\"Failed to fetch patient\"}";


            exchange.getResponseHeaders()
                    .set(
                            "Access-Control-Allow-Origin",
                            "*"
                    );


            exchange.sendResponseHeaders(
                    500,
                    response.getBytes().length
            );


            OutputStream output =
                    exchange.getResponseBody();


            output.write(
                    response.getBytes()
            );


            output.close();
        }
    }


    // ==========================================
    // POST - ADD NEW PATIENT
    // ==========================================

    public static void addPatient(
            HttpExchange exchange)
            throws IOException {

        try {

            InputStream input =
                    exchange.getRequestBody();


            String body =
                    new String(
                            input.readAllBytes()
                    );


            System.out.println(
                    "Received data:"
            );

            System.out.println(body);


            String name =
                    getValue(
                            body,
                            "name"
                    );


            int age =
                    Integer.parseInt(
                            getValue(
                                    body,
                                    "age"
                            )
                    );


            String gender =
                    getValue(
                            body,
                            "gender"
                    );


            String phone =
                    getValue(
                            body,
                            "phone"
                    );


            String email =
                    getValue(
                            body,
                            "email"
                    );


            String address =
                    getValue(
                            body,
                            "address"
                    );


            String bloodGroup =
                    getValue(
                            body,
                            "blood_group"
                    );


            Connection connection =
                    DatabaseConnection.getConnection();


            String sql =
                    "INSERT INTO patients " +
                    "(name, age, gender, phone, email, address, blood_group) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";


            PreparedStatement statement =
                    connection.prepareStatement(sql);


            statement.setString(
                    1,
                    name
            );

            statement.setInt(
                    2,
                    age
            );

            statement.setString(
                    3,
                    gender
            );

            statement.setString(
                    4,
                    phone
            );

            statement.setString(
                    5,
                    email
            );

            statement.setString(
                    6,
                    address
            );

            statement.setString(
                    7,
                    bloodGroup
            );


            statement.executeUpdate();


            statement.close();
            connection.close();


            String response =
                    "{\"message\":\"Patient added successfully\"}";


            exchange.getResponseHeaders()
                    .set(
                            "Content-Type",
                            "application/json"
                    );


            exchange.getResponseHeaders()
                    .set(
                            "Access-Control-Allow-Origin",
                            "*"
                    );


            exchange.sendResponseHeaders(
                    201,
                    response.getBytes().length
            );


            OutputStream output =
                    exchange.getResponseBody();


            output.write(
                    response.getBytes()
            );


            output.close();


        } catch (Exception e) {

            e.printStackTrace();


            String response =
                    "{\"error\":\"Failed to add patient\"}";


            exchange.getResponseHeaders()
                    .set(
                            "Access-Control-Allow-Origin",
                            "*"
                    );


            exchange.sendResponseHeaders(
                    500,
                    response.getBytes().length
            );


            OutputStream output =
                    exchange.getResponseBody();


            output.write(
                    response.getBytes()
            );


            output.close();
        }
    }


    // ==========================================
    // SIMPLE JSON VALUE READER
    // ==========================================

    public static String getValue(
            String json,
            String key) {

        String search =
                "\"" + key + "\":\"";


        int start =
                json.indexOf(search);


        if (start != -1) {

            start += search.length();


            int end =
                    json.indexOf(
                            "\"",
                            start
                    );


            return json.substring(
                    start,
                    end
            );
        }


        search =
                "\"" + key + "\":";


        start =
                json.indexOf(search);


        start += search.length();


        int end =
                json.indexOf(
                        ",",
                        start
                );


        if (end == -1) {

            end =
                    json.indexOf(
                            "}",
                            start
                    );
        }


        return json.substring(
                start,
                end
        ).trim();
    }
}
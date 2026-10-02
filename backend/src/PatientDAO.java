import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientDAO {

    // Add new patient
    public void addPatient(Patient patient) {

        String sql = "INSERT INTO patients " +
                     "(name, age, gender, phone, email, address, blood_group) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, patient.getName());
            statement.setInt(2, patient.getAge());
            statement.setString(3, patient.getGender());
            statement.setString(4, patient.getPhone());
            statement.setString(5, patient.getEmail());
            statement.setString(6, patient.getAddress());
            statement.setString(7, patient.getBloodGroup());

            statement.executeUpdate();

            System.out.println("Patient added successfully!");

        } catch (SQLException e) {
            System.out.println("Failed to add patient!");
            e.printStackTrace();
        }
    }

    // Display all patients
    public void getAllPatients() {

        String sql = "SELECT * FROM patients";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {

                System.out.println(
                    result.getInt("patient_id") + " | " +
                    result.getString("name") + " | " +
                    result.getInt("age") + " | " +
                    result.getString("gender") + " | " +
                    result.getString("phone") + " | " +
                    result.getString("email") + " | " +
                    result.getString("address") + " | " +
                    result.getString("blood_group")
                );
            }

        } catch (SQLException e) {
            System.out.println("Failed to fetch patients!");
            e.printStackTrace();
        }
    }
}
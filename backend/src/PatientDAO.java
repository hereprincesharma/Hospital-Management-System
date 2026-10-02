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
    // Update patient
    public void updatePatient(Patient patient) {
    
        String sql = "UPDATE patients SET name = ?, age = ?, gender = ?, " +
                     "phone = ?, email = ?, address = ?, blood_group = ? " +
                     "WHERE patient_id = ?";
    
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
    
            statement.setString(1, patient.getName());
            statement.setInt(2, patient.getAge());
            statement.setString(3, patient.getGender());
            statement.setString(4, patient.getPhone());
            statement.setString(5, patient.getEmail());
            statement.setString(6, patient.getAddress());
            statement.setString(7, patient.getBloodGroup());
            statement.setInt(8, patient.getPatientId());
    
            int rowsUpdated = statement.executeUpdate();
    
            if (rowsUpdated > 0) {
                System.out.println("Patient updated successfully!");
            } else {
                System.out.println("Patient not found!");
            }
    
        } catch (SQLException e) {
            System.out.println("Failed to update patient!");
            e.printStackTrace();
        }
    }
    
    
    // Delete patient
    public void deletePatient(int patientId) {
    
        String sql = "DELETE FROM patients WHERE patient_id = ?";
    
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
    
            statement.setInt(1, patientId);
    
            int rowsDeleted = statement.executeUpdate();
    
            if (rowsDeleted > 0) {
                System.out.println("Patient deleted successfully!");
            } else {
                System.out.println("Patient not found!");
            }
    
        } catch (SQLException e) {
            System.out.println("Failed to delete patient!");
            e.printStackTrace();
        }
    }
}
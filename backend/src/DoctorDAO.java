import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorDAO {

    // Add new doctor
    public void addDoctor(Doctor doctor) {

        String sql = "INSERT INTO doctors " +
                     "(name, specialization, experience, phone, email, availability, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getSpecialization());
            statement.setInt(3, doctor.getExperience());
            statement.setString(4, doctor.getPhone());
            statement.setString(5, doctor.getEmail());
            statement.setString(6, doctor.getAvailability());
            statement.setString(7, doctor.getStatus());

            statement.executeUpdate();

            System.out.println("Doctor added successfully!");

        } catch (SQLException e) {
            System.out.println("Failed to add doctor!");
            e.printStackTrace();
        }
    }

    // Display all doctors
    public void getAllDoctors() {

        String sql = "SELECT * FROM doctors";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {

                System.out.println(
                    result.getInt("doctor_id") + " | " +
                    result.getString("name") + " | " +
                    result.getString("specialization") + " | " +
                    result.getInt("experience") + " years | " +
                    result.getString("phone") + " | " +
                    result.getString("email") + " | " +
                    result.getString("availability") + " | " +
                    result.getString("status")
                );
            }

        } catch (SQLException e) {
            System.out.println("Failed to fetch doctors!");
            e.printStackTrace();
        }
    }

    // Update doctor
    public void updateDoctor(Doctor doctor) {

        String sql = "UPDATE doctors SET name = ?, specialization = ?, " +
                     "experience = ?, phone = ?, email = ?, " +
                     "availability = ?, status = ? " +
                     "WHERE doctor_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getSpecialization());
            statement.setInt(3, doctor.getExperience());
            statement.setString(4, doctor.getPhone());
            statement.setString(5, doctor.getEmail());
            statement.setString(6, doctor.getAvailability());
            statement.setString(7, doctor.getStatus());
            statement.setInt(8, doctor.getDoctorId());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Doctor updated successfully!");
            } else {
                System.out.println("Doctor not found!");
            }

        } catch (SQLException e) {
            System.out.println("Failed to update doctor!");
            e.printStackTrace();
        }
    }

    // Delete doctor
    public void deleteDoctor(int doctorId) {

        String sql = "DELETE FROM doctors WHERE doctor_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, doctorId);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Doctor deleted successfully!");
            } else {
                System.out.println("Doctor not found!");
            }

        } catch (SQLException e) {
            System.out.println("Failed to delete doctor!");
            e.printStackTrace();
        }
    }
}
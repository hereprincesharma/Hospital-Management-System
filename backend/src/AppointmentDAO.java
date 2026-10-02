import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentDAO {

    // Add new appointment
    public void addAppointment(Appointment appointment) {

        String sql = "INSERT INTO appointments " +
                     "(patient_id, doctor_id, appointment_date, appointment_time, reason, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, appointment.getPatientId());
            statement.setInt(2, appointment.getDoctorId());
            statement.setString(3, appointment.getAppointmentDate());
            statement.setString(4, appointment.getAppointmentTime());
            statement.setString(5, appointment.getReason());
            statement.setString(6, appointment.getStatus());

            statement.executeUpdate();

            System.out.println("Appointment added successfully!");

        } catch (SQLException e) {
            System.out.println("Failed to add appointment!");
            e.printStackTrace();
        }
    }

    // Display all appointments
    public void getAllAppointments() {

        String sql = "SELECT * FROM appointments";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {

                System.out.println(
                    result.getInt("appointment_id") + " | " +
                    "Patient ID: " + result.getInt("patient_id") + " | " +
                    "Doctor ID: " + result.getInt("doctor_id") + " | " +
                    result.getString("appointment_date") + " | " +
                    result.getString("appointment_time") + " | " +
                    result.getString("reason") + " | " +
                    result.getString("status")
                );
            }

        } catch (SQLException e) {
            System.out.println("Failed to fetch appointments!");
            e.printStackTrace();
        }
    }

    // Update appointment
    public void updateAppointment(Appointment appointment) {

        String sql = "UPDATE appointments SET patient_id = ?, doctor_id = ?, " +
                     "appointment_date = ?, appointment_time = ?, " +
                     "reason = ?, status = ? " +
                     "WHERE appointment_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, appointment.getPatientId());
            statement.setInt(2, appointment.getDoctorId());
            statement.setString(3, appointment.getAppointmentDate());
            statement.setString(4, appointment.getAppointmentTime());
            statement.setString(5, appointment.getReason());
            statement.setString(6, appointment.getStatus());
            statement.setInt(7, appointment.getAppointmentId());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Appointment updated successfully!");
            } else {
                System.out.println("Appointment not found!");
            }

        } catch (SQLException e) {
            System.out.println("Failed to update appointment!");
            e.printStackTrace();
        }
    }

    // Delete appointment
    public void deleteAppointment(int appointmentId) {

        String sql = "DELETE FROM appointments WHERE appointment_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, appointmentId);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Appointment deleted successfully!");
            } else {
                System.out.println("Appointment not found!");
            }

        } catch (SQLException e) {
            System.out.println("Failed to delete appointment!");
            e.printStackTrace();
        }
    }
}
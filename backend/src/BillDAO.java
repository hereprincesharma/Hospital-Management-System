import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillDAO {

    // Add new bill
    public void addBill(Bill bill) {

        String sql = "INSERT INTO bills " +
                     "(patient_id, doctor_id, service, amount, bill_date, payment_status) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, bill.getPatientId());
            statement.setInt(2, bill.getDoctorId());
            statement.setString(3, bill.getService());
            statement.setDouble(4, bill.getAmount());
            statement.setString(5, bill.getBillDate());
            statement.setString(6, bill.getPaymentStatus());

            statement.executeUpdate();

            System.out.println("Bill added successfully!");

        } catch (SQLException e) {
            System.out.println("Failed to add bill!");
            e.printStackTrace();
        }
    }

    // Display all bills
    public void getAllBills() {

        String sql = "SELECT * FROM bills";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {

                System.out.println(
                    result.getInt("bill_id") + " | " +
                    "Patient ID: " + result.getInt("patient_id") + " | " +
                    "Doctor ID: " + result.getInt("doctor_id") + " | " +
                    result.getString("service") + " | " +
                    "₹" + result.getDouble("amount") + " | " +
                    result.getString("bill_date") + " | " +
                    result.getString("payment_status")
                );
            }

        } catch (SQLException e) {
            System.out.println("Failed to fetch bills!");
            e.printStackTrace();
        }
    }

    // Update bill
    public void updateBill(Bill bill) {

        String sql = "UPDATE bills SET patient_id = ?, doctor_id = ?, " +
                     "service = ?, amount = ?, bill_date = ?, " +
                     "payment_status = ? WHERE bill_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, bill.getPatientId());
            statement.setInt(2, bill.getDoctorId());
            statement.setString(3, bill.getService());
            statement.setDouble(4, bill.getAmount());
            statement.setString(5, bill.getBillDate());
            statement.setString(6, bill.getPaymentStatus());
            statement.setInt(7, bill.getBillId());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Bill updated successfully!");
            } else {
                System.out.println("Bill not found!");
            }

        } catch (SQLException e) {
            System.out.println("Failed to update bill!");
            e.printStackTrace();
        }
    }

    // Delete bill
    public void deleteBill(int billId) {

        String sql = "DELETE FROM bills WHERE bill_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, billId);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Bill deleted successfully!");
            } else {
                System.out.println("Bill not found!");
            }

        } catch (SQLException e) {
            System.out.println("Failed to delete bill!");
            e.printStackTrace();
        }
    }
}

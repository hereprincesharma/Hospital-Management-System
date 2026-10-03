import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicineDAO {

    // Add new medicine
    public void addMedicine(Medicine medicine) {

        String sql = "INSERT INTO medicines " +
                     "(name, category, quantity, price, expiry_date, stock_status) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, medicine.getName());
            statement.setString(2, medicine.getCategory());
            statement.setInt(3, medicine.getQuantity());
            statement.setDouble(4, medicine.getPrice());
            statement.setString(5, medicine.getExpiryDate());
            statement.setString(6, medicine.getStockStatus());

            statement.executeUpdate();

            System.out.println("Medicine added successfully!");

        } catch (SQLException e) {
            System.out.println("Failed to add medicine!");
            e.printStackTrace();
        }
    }

    // Display all medicines
    public void getAllMedicines() {

        String sql = "SELECT * FROM medicines";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {

                System.out.println(
                    result.getInt("medicine_id") + " | " +
                    result.getString("name") + " | " +
                    result.getString("category") + " | " +
                    result.getInt("quantity") + " | " +
                    result.getDouble("price") + " | " +
                    result.getString("expiry_date") + " | " +
                    result.getString("stock_status")
                );
            }

        } catch (SQLException e) {
            System.out.println("Failed to fetch medicines!");
            e.printStackTrace();
        }
    }

    // Update medicine
    public void updateMedicine(Medicine medicine) {

        String sql = "UPDATE medicines SET name = ?, category = ?, " +
                     "quantity = ?, price = ?, expiry_date = ?, " +
                     "stock_status = ? WHERE medicine_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, medicine.getName());
            statement.setString(2, medicine.getCategory());
            statement.setInt(3, medicine.getQuantity());
            statement.setDouble(4, medicine.getPrice());
            statement.setString(5, medicine.getExpiryDate());
            statement.setString(6, medicine.getStockStatus());
            statement.setInt(7, medicine.getMedicineId());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Medicine updated successfully!");
            } else {
                System.out.println("Medicine not found!");
            }

        } catch (SQLException e) {
            System.out.println("Failed to update medicine!");
            e.printStackTrace();
        }
    }

    // Delete medicine
    public void deleteMedicine(int medicineId) {

        String sql = "DELETE FROM medicines WHERE medicine_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, medicineId);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Medicine deleted successfully!");
            } else {
                System.out.println("Medicine not found!");
            }

        } catch (SQLException e) {
            System.out.println("Failed to delete medicine!");
            e.printStackTrace();
        }
    }
}
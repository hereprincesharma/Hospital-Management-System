public class MedicineTest {

    public static void main(String[] args) {

        Medicine medicine = new Medicine(
                "Paracetamol",
                "Painkiller",
                100,
                5.50,
                "2027-12-31",
                "Available"
        );

        MedicineDAO medicineDAO = new MedicineDAO();

        medicineDAO.addMedicine(medicine);

        System.out.println("\nAll Medicines:");
        medicineDAO.getAllMedicines();

        System.out.println("\nTest completed.");
    }
}
// public class MedicineTest {

//     public static void main(String[] args) {

//         Medicine medicine = new Medicine(
//                 "Paracetamol",
//                 "Painkiller",
//                 100,
//                 5.50,
//                 "2027-12-31",
//                 "Available"
//         );

//         MedicineDAO medicineDAO = new MedicineDAO();

//         medicineDAO.addMedicine(medicine);

//         System.out.println("\nAll Medicines:");
//         medicineDAO.getAllMedicines();

//         System.out.println("\nTest completed.");
//     }
// }

// checking update and delete 
public class MedicineTest {

    public static void main(String[] args) {

        MedicineDAO medicineDAO = new MedicineDAO();

        // Update medicine with ID 1
        Medicine medicine = new Medicine(
                "Paracetamol 500mg",
                "Painkiller",
                150,
                6.00,
                "2028-06-30",
                "Available"
        );

        medicine.setMedicineId(1);

        medicineDAO.updateMedicine(medicine);

        //delete function
        medicineDAO.deleteMedicine(1);

        System.out.println("\nAll Medicines:");
        medicineDAO.getAllMedicines();

        System.out.println("\nTest completed.");
    }
}
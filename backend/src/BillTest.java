// public class BillTest {

//     public static void main(String[] args) {

//         Bill bill = new Bill(
//                 1,
//                 2,
//                 "Doctor Consultation",
//                 500.00,
//                 "2026-10-03",
//                 "Paid"
//         );

//         BillDAO billDAO = new BillDAO();

//         billDAO.addBill(bill);

//         System.out.println("\nAll Bills:");
//         billDAO.getAllBills();

//         System.out.println("\nTest completed.");
//     }
// }


// update and delete function
public class BillTest {

    public static void main(String[] args) {

        BillDAO billDAO = new BillDAO();

        // Update bill with ID 1
        Bill bill = new Bill(
                1,
                2,
                "Doctor Consultation + Checkup",
                750.00,
                "2026-10-03",
                "Paid"
        );

        bill.setBillId(1);

        billDAO.updateBill(bill);

        //delete 
        billDAO.deleteBill(1);

        System.out.println("\nAll Bills:");
        billDAO.getAllBills();

        System.out.println("\nTest completed.");
    }
}
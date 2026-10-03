public class PatientTest {

    public static void main(String[] args) {

        Patient patient = new Patient(
                "Rahul Sharma",
                25,
                "Male",
                "9876543210",
                "rahul@gmail.com",
                "Mumbai",
                "B+"
        );

        PatientDAO patientDAO = new PatientDAO();

        patientDAO.addPatient(patient);

        System.out.println("\nAll Patients:");
        patientDAO.getAllPatients();

        // System.out.println("\nTest completed.");
    }
}

// public class PatientTest {

//     public static void main(String[] args) {

//         PatientDAO patientDAO = new PatientDAO();

//         // Update patient with ID 1
//         Patient patient = new Patient(
//                 "Rahul Sharma Updated",
//                 26,
//                 "Male",
//                 "9999999999",
//                 "rahulupdated@gmail.com",
//                 "Thane",
//                 "O+"
//         );

//         patient.setPatientId(1);

//         patientDAO.updatePatient(patient);

//         //Delete patient with id 3

//         patientDAO.deletePatient(3);

//         System.out.println("\nAll Patients:");
//         patientDAO.getAllPatients();

//         System.out.println("\nTest completed.");
//     }
// }
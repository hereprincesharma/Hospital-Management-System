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

        System.out.println("\nTest completed.");
    }
}
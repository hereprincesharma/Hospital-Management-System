public class DoctorTest {

    public static void main(String[] args) {

        Doctor doctor = new Doctor(
                "Dr. Amit Sharma",
                "Cardiologist",
                10,
                "9876543210",
                "amit@gmail.com",
                "10 AM - 2 PM",
                "Active"
        );

        DoctorDAO doctorDAO = new DoctorDAO();

        doctorDAO.addDoctor(doctor);

        System.out.println("\nAll Doctors:");
        doctorDAO.getAllDoctors();

        System.out.println("\nTest completed.");
    }
}
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

    

        // Update doctor with ID 1
        // Doctor doctor = new Doctor(
        //         "Dr. Amit Sharma Updated",
        //         "Neurologist",
        //         12,
        //         "9999999999",
        //         "amitupdated@gmail.com",
        //         "11 AM - 3 PM",
        //         "Active"
        // );

        // doctor.setDoctorId(1);

        // doctorDAO.updateDoctor(doctor);

        // Delete doctor with ID 1
        // doctorDAO.deleteDoctor(1);

        System.out.println("\nAll Doctors:");
        doctorDAO.getAllDoctors();

        System.out.println("\nTest completed.");
    }
}
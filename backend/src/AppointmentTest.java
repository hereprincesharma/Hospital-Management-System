public class AppointmentTest {

    public static void main(String[] args) {

        Appointment appointment = new Appointment(
                1,
                2,
                "2026-10-05",
                "10:30:00",
                "Regular Checkup",
                "Pending"
        );

        AppointmentDAO appointmentDAO = new AppointmentDAO();

        appointmentDAO.addAppointment(appointment);

        System.out.println("\nAll Appointments:");
        appointmentDAO.getAllAppointments();

        System.out.println("\nTest completed.");
    }
}
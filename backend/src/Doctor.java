public class Doctor {

    private int doctorId;
    private String name;
    private String specialization;
    private int experience;
    private String phone;
    private String email;
    private String availability;
    private String status;

    public Doctor() {
    }

    public Doctor(String name, String specialization, int experience,
                   String phone, String email, String availability,
                   String status) {

        this.name = name;
        this.specialization = specialization;
        this.experience = experience;
        this.phone = phone;
        this.email = email;
        this.availability = availability;
        this.status = status;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
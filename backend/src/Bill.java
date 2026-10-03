public class Bill {

    private int billId;
    private int patientId;
    private int doctorId;
    private String service;
    private double amount;
    private String billDate;
    private String paymentStatus;

    public Bill() {
    }

    public Bill(int patientId, int doctorId, String service,
                double amount, String billDate, String paymentStatus) {

        this.patientId = patientId;
        this.doctorId = doctorId;
        this.service = service;
        this.amount = amount;
        this.billDate = billDate;
        this.paymentStatus = paymentStatus;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
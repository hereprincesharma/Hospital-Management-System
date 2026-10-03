public class Medicine{

    private int medicineId;
    private String name;
    private String category;
    private int quantity;
    private double price;
    private String expiryDate;
    private String stockStatus;

    public Medicine(){

    }

    public Medicine(String name,String category,int quantity,
        double price,String expiryDate,String stockStatus)
    
        {
            this.name=name;
            this.category=category;
            this.quantity=quantity;
            this.price=price;
            this.expiryDate=expiryDate;
            this.stockStatus=stockStatus;
        }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }
    
}
package dto;

public class CustomerDTO {
    private String customerId;
    private String customerName;
    private String address;
    private String salary;

    public CustomerDTO() {
    }

    public CustomerDTO(String customerId, String customerName, String address, String salary) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.salary = salary;
    }


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}

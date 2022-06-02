package dto;

import entity.OrderDetails;

import java.time.LocalDate;
import java.util.ArrayList;

public class OrderDTO {
    private String orderId;
    private String customerId;
    private LocalDate orderDate;
    private String total;
    private String balance;
    private ArrayList<OrderDetails> items;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, String customerId, LocalDate orderDate, String total, String balance, ArrayList<OrderDetails> items) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.total = total;
        this.balance = balance;
        this.items = items;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public ArrayList<OrderDetails> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderDetails> items) {
        this.items = items;
    }
}

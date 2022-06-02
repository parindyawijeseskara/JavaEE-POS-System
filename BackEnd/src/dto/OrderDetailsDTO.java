package dto;

import java.time.LocalDate;

public class OrderDetailsDTO {
    private String orderId;
    private String itemCode;
    private String itemName;
    private String orderQty;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(String orderId, String itemCode, String itemName, String orderQty) {
        this.orderId = orderId;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.orderQty = orderQty;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(String orderQty) {
        this.orderQty = orderQty;
    }
}

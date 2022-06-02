package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;
import dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;

public interface PlaceOrderBO extends SuperBO {
    CustomerDTO getCustomer(String customerId, Connection connection) throws SQLException, ClassNotFoundException;

    ItemDTO getItem(String itemCode, Connection connection) throws SQLException, ClassNotFoundException;
}

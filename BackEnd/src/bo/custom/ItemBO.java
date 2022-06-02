package bo.custom;

import bo.SuperBO;
import dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    ArrayList<ItemDTO> getAllItems(Connection connection) throws SQLException, ClassNotFoundException;

    boolean deleteItem(String itemCode,Connection connection) throws SQLException, ClassNotFoundException;

    boolean addItem(ItemDTO dto, Connection connection) throws SQLException, ClassNotFoundException;

    boolean updateItem(ItemDTO dto, Connection connection) throws SQLException, ClassNotFoundException;

    ItemDTO ifItemExist(String itemCode, Connection connection) throws SQLException, ClassNotFoundException;
}

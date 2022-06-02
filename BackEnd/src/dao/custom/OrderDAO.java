package dao.custom;

import dao.CrudDAO;
import entity.Order;

import java.sql.Connection;
import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order,String> {
    boolean ifOrderExist(Connection connection) throws SQLException, ClassNotFoundException;

}

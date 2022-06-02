package dao.custom;

import dao.CrudDAO;
import entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;

public interface CustomerDAO  extends CrudDAO<Customer,String> {
    boolean ifExistCustomer(Connection connection) throws SQLException, ClassNotFoundException;

}

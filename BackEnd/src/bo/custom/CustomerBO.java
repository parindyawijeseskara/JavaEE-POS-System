package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    ArrayList<CustomerDTO> getAllCustomer(Connection connection) throws SQLException, ClassNotFoundException;

    boolean addCustomer(CustomerDTO customerDTO,Connection connection) throws SQLException,ClassNotFoundException;

    boolean updateCustomer(CustomerDTO customerDTO, Connection connection) throws SQLException, ClassNotFoundException;

    CustomerDTO ifCustomerExist(String customerId, Connection connection) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String customerId, Connection connection) throws SQLException, ClassNotFoundException;

}

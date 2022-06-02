package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import entity.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean ifExistCustomer(Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean add(Customer customer, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection,"insert into customer values(?,?,?,?)",customer.getCustomerId(),customer.getCustomerName(),customer.getAddress(),customer.getSalary());

    }

    @Override
    public boolean delete(String customerId, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection,"delete from customer where customerId=?",customerId);

    }

    @Override
    public boolean update(Customer customer, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection,"update customer set customerName=?,address=?,salary=? WHERE customerId=?",customer.getCustomerName(),customer.getAddress(),customer.getSalary(),customer.getCustomerId());

    }

    @Override
    public Customer search(String customerId, Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery(connection,"select * from customer where customerId =?",customerId);
        if (rst.next()){
            return new Customer(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4));

        }
        return null;


    }

    @Override
    public ArrayList<Customer> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers=new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery(connection, "select* from customer");
        while (rst.next()){
            customers.add(new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)));
        }
        return customers;
    }
}

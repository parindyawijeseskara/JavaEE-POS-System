package bo.custom.impl;

import bo.custom.CustomerBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;
import entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAOType(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomer(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        ArrayList<Customer> customers = customerDAO.getAll(connection);

        for (Customer customer : customers) {
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomerId(), customer.getCustomerName(), customer.getAddress(), customer.getSalary()
            );
            allCustomers.add(customerDTO);
        }
        return allCustomers;
    }

    @Override
    public boolean addCustomer(CustomerDTO customerDTO, Connection connection) throws SQLException, ClassNotFoundException {
        Customer customer = new Customer(customerDTO.getCustomerId(),customerDTO.getCustomerName(),customerDTO.getAddress(),customerDTO.getSalary());
        return customerDAO.add(customer, connection);
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO, Connection connection) throws SQLException, ClassNotFoundException {
        Customer customer = new Customer(customerDTO.getCustomerId(),customerDTO.getCustomerName(),customerDTO.getAddress(),customerDTO.getSalary());
        return customerDAO.update(customer, connection);
    }

    @Override
    public CustomerDTO ifCustomerExist(String customerId, Connection connection) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(customerId,connection);
        CustomerDTO customerDto = new CustomerDTO(
                customer.getCustomerId(), customer.getCustomerName(), customer.getAddress(), customer.getSalary()
        );
        return customerDto;
    }

    @Override
    public boolean deleteCustomer(String customerId, Connection connection) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(customerId, connection);
    }
}

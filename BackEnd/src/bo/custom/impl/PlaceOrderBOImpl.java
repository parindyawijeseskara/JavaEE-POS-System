package bo.custom.impl;

import bo.custom.PlaceOrderBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dao.custom.ItemDAO;
import dao.custom.OrderDAO;
import dao.custom.OrderDetailsDAO;
import dto.CustomerDTO;
import dto.ItemDTO;
import entity.Customer;
import entity.Item;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAOType(DAOFactory.DAOTypes.CUSTOMER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAOType(DAOFactory.DAOTypes.ITEM);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAOType(DAOFactory.DAOTypes.ORDER);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAOType(DAOFactory.DAOTypes.ORDERDETAILS);


    @Override
    public CustomerDTO getCustomer(String customerId, Connection connection) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(customerId, connection);
        CustomerDTO customerDto = new CustomerDTO(
                customer.getCustomerId(), customer.getCustomerName(), customer.getAddress(), customer.getSalary()
        );
        return customerDto;
    }

    @Override
    public ItemDTO getItem(String itemCode, Connection connection) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.search(itemCode, connection);
        ItemDTO itemDTO = new ItemDTO(
                item.getItemCode(), item.getItemName(),item.getItemQty(),item.getItemPrice()
        );
        return itemDTO;
    }
}

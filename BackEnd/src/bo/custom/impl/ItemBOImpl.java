package bo.custom.impl;

import bo.custom.ItemBO;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dto.CustomerDTO;
import dto.ItemDTO;
import entity.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAOType(DAOFactory.DAOTypes.ITEM);


    @Override
    public ArrayList<ItemDTO> getAllItems(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allItems = new ArrayList<>();

        ArrayList<Item> items = itemDAO.getAll(connection);

        for (Item item : items) {
            ItemDTO itemDTO = new ItemDTO(item.getItemCode(),item.getItemName(),item.getItemQty(),item.getItemPrice());
            allItems.add(itemDTO);
        }
        return allItems;
    }

    @Override
    public boolean deleteItem(String itemCode, Connection connection) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(itemCode, connection);
    }

    @Override
    public boolean addItem(ItemDTO itemDTO, Connection connection) throws SQLException, ClassNotFoundException {
        Item item = new Item(itemDTO.getItemCode(),itemDTO.getItemName(),itemDTO.getItemQty(),itemDTO.getItemPrice());
        return itemDAO.add(item, connection);
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO, Connection connection) throws SQLException, ClassNotFoundException {
        Item item = new Item(itemDTO.getItemCode(),itemDTO.getItemName(),itemDTO.getItemQty(),itemDTO.getItemPrice());

        return itemDAO.update(item, connection);
    }

    @Override
    public ItemDTO ifItemExist(String itemCode, Connection connection) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.search(itemCode, connection);
        ItemDTO itemDTO = new ItemDTO(
                item.getItemCode(),item.getItemName(),item.getItemQty(),item.getItemPrice()
        );
        return itemDTO;
    }
}

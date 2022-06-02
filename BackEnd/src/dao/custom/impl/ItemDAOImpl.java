package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ItemDAO;
import entity.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public String ifItemExist(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Item item, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection,"insert into item values(?,?,?,?)",item.getItemCode(),item.getItemName(),item.getItemQty(),item.getItemPrice());

    }

    @Override
    public boolean delete(String itemCode, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection,"delete from item where id=?",itemCode);

    }

    @Override
    public boolean update(Item item, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection,"update item set itemName=?,itemQty=?,itemPrice=? WHERE id=?",item.getItemName(),item.getItemQty(),item.getItemPrice(),item.getItemCode());

    }

    @Override
    public Item search(String itemCode, Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery(connection,"select * from item where id =?", itemCode);
        if (rst.next()){
            return new Item(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4));

        }
        return null;
    }

    @Override
    public ArrayList<Item> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<Item> items=new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery(connection, "select * from item");
        while (rst.next()){
            items.add(new Item(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
        }
        return items;
    }
}

package dao.custom;

import dao.CrudDAO;
import entity.Item;

import java.sql.Connection;
import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item,String> {
    String ifItemExist(Connection connection) throws SQLException, ClassNotFoundException;

}

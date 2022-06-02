package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {
    private static PreparedStatement generatePreparedStatement(Connection connection, String query , Object... args) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i+1,args[i]);
        }
        return preparedStatement;
    }

    public static boolean executeUpdate(Connection connection, String query , Object... args) throws SQLException, ClassNotFoundException {
        return generatePreparedStatement(connection,query,args).executeUpdate()>0;
    }


    public static ResultSet executeQuery(Connection connection, String query , Object... args) throws SQLException, ClassNotFoundException {

        return generatePreparedStatement(connection,query,args).executeQuery();
    }

}

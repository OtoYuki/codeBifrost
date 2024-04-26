package controller.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.StringUtils;

public class DatabaseController {

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        // Load the JDBC driver
        Class.forName(StringUtils.DRIVER_NAME);

        // Establish a connection
        return DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME,
                StringUtils.LOCALHOST_PASSWORD);
    }
}

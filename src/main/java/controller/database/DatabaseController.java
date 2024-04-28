package controller.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.LoginModel;
import util.StringUtils;

public class DatabaseController {

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        // Load the JDBC driver
        Class.forName(StringUtils.DRIVER_NAME);

        // Establish a connection
        return DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME,
                StringUtils.LOCALHOST_PASSWORD);
    }

    public int getStudentLoginInfo(LoginModel loginModel) {
        // Try-catch block to handle potential SQL or ClassNotFoundException
        try {
            // Prepare a statement using the predefined SQL query for login
            PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_LOGIN_USER_CHECK);

            // Set the username in the query
            st.setString(1, loginModel.getUsername());

            // Execute the query
            ResultSet result = st.executeQuery();

            // Check if the result set is empty
            if (result.next()) {
                // Get the Username from the Database
                String userDB = result.getString(StringUtils.USERNAME);

                // Get the Password from the Database
                String passDB = result.getString(StringUtils.PASSWORD);

                // Check if the username and password match
                if (userDB.equals(loginModel.getUsername()) && passDB.equals(loginModel.getPassword())) {
                    // Return 1 if the username and password match
                    return 1;
                } else {
                    // Return 0 if the password does not match
                    return 0;
                }
            } else {
                // Return -1 if the username does not exist
                return -1;
            }

            // Catch SQLException and ClassNotFoundException
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Return -2 if an exception occurs
            return -2;
        }

    }
}

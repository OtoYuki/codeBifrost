package controller.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.LoginModel;
import model.PasswordEncryptionWithAes;
import model.UserModel;
import util.StringUtils;

public class DatabaseController {

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        // Load the JDBC driver
        Class.forName(StringUtils.DRIVER_NAME);

        // Establish a connection
        return DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME,
                StringUtils.LOCALHOST_PASSWORD);
    }

    public int registerUser(UserModel user) {
        // Try-Catch block to handle potential SQL or ClassNotFoundException
        try {
            // Prepare a Statement using the predefined SQL query for registration
            PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_REGISTER_USER);

            // Set the parameters in the query
            stmt.setInt(1, user.getUser_id());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getFirst_name());
            stmt.setString(4, user.getLast_name());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, PasswordEncryptionWithAes.encrypt(user.getUsername(), user.getPassword()));
            stmt.setBoolean(7, user.getIs_admin());
            stmt.setString(8, user.getPhonenumber());

            // Execute the Update Statement and Store the Number of Rows Affected
            int result = stmt.executeUpdate();

            // Check if the result is greater than 0 (i.e atleast 1 row is affected)
            if (result > 0) {
                return 1; // Registration Successful
            } else {
                return 0; // Registration Failed
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return -1; // Exception Occurred
        }
    }

    /**
     * This method is used to check the login credentials of a student. It takes a
     * `LoginModel` object as input and returns an integer value based on the
     * result of the login check.
     * 
     * @param loginModel The `LoginModel` object containing the username and
     *                   password of the student.
     * @return An integer value based on the result of the login check: 1 if the
     *         username and password match, 0 if the password does not match, -1 if
     *         the username does not exist, -2 if an exception occurs.
     */

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
                String encryptedPwd = result.getString(StringUtils.PASSWORD);

                String decryptedPwd = PasswordEncryptionWithAes.decrypt(encryptedPwd, userDB);

                // Check if the username and password match
                if (userDB.equals(loginModel.getUsername()) && decryptedPwd.equals(loginModel.getPassword())) {
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

    public boolean isAdmin(String username) {
        try {
            // Prepare a statement using the predefined SQL query for login
            PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_ADMIN_CHECK);

            // Set the username in the query
            st.setString(1, username);

            // Execute the query
            ResultSet result = st.executeQuery();

            // Check if the result set is empty
            return result.next();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}

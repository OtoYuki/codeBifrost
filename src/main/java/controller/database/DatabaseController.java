package controller.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.LoginModel;
import model.PasswordEncryptionWithAes;
import model.RoadmapModel;
import model.UserModel;
import util.StringUtils;

public class DatabaseController {

    /**
     * This method is used to check if a user is an admin. It takes a
     * username as input and returns a boolean value based on the
     * result of the admin check.
     * 
     * @param username The username of the user.
     * @return A boolean value based on the result of the admin check:
     *         true if the user is an admin, false if the user is not an admin or if
     *         an exception occurs.
     */
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        // Load the JDBC driver
        Class.forName(StringUtils.DRIVER_NAME);

        // Establish a connection
        return DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME,
                StringUtils.LOCALHOST_PASSWORD);
    }

    /**
     * This method is used to check if a user is an admin. It takes a
     * username as input and returns a boolean value based on the
     * result of the admin check.
     * 
     * @param username The username of the user.
     * @return A boolean value based on the result of the admin check:
     *         true if the user is an admin, false if the user is not an admin or if
     *         an exception occurs.
     */
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
            stmt.setString(8, user.getPhoneNumber());

            if (user.getProfilePicturePath() != null) {
                stmt.setString(9, user.getProfilePicturePath());
            } else {
                stmt.setNull(9, java.sql.Types.BLOB);
            }

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

    /**
     * This method is used to check if a user is an admin. It takes a
     * username as input and returns a boolean value based on the
     * result of the admin check.
     * 
     * @param username The username of the user.
     * @return A boolean value based on the result of the admin check:
     *         true if the user is an admin, false if the user is not an admin or if
     *         an exception occurs.
     */
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

    /**
     * This method is used to check if a user is an admin. It takes a
     * username as input and returns a boolean value based on the
     * result of the admin check.
     * 
     * @param username The username of the user.
     * @return A boolean value based on the result of the admin check:
     *         true if the user is an admin, false if the user is not an admin or if
     *         an exception occurs.
     */
    public ArrayList<UserModel> getAllUsersInfo() {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_GET_ALL_USERS);
            ResultSet result = stmt.executeQuery();

            ArrayList<UserModel> users = new ArrayList<UserModel>();

            while (result.next()) {
                UserModel user = new UserModel();
                user.setUser_id(result.getInt("user_id"));
                user.setUsername(result.getString("username"));
                user.setFirst_name(result.getString("first_name"));
                user.setLast_name(result.getString("last_name"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
                user.setIs_admin(result.getBoolean("is_admin"));
                user.setPhoneNumber(result.getString("phonenumber"));
                user.setProfilePicturePath(result.getString("profile_picture"));
                users.add(user);

            }
            return users;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<RoadmapModel> getAllRoadmapModels() {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_GET_ALL_ROADMAPS);
            ResultSet result = stmt.executeQuery();

            ArrayList<RoadmapModel> roadmaps = new ArrayList<RoadmapModel>();

            while (result.next()) {
                RoadmapModel roadmap = new RoadmapModel();
                roadmap.setRoadmapId(result.getInt("roadmap_id"));
                roadmap.setTitle(result.getString("title"));
                roadmap.setDescription(result.getString("description"));
                roadmap.setSkillLevel(result.getString("skill_level"));
                roadmap.setImagePath(result.getString("image_path"));
                roadmaps.add(roadmap);
            }
            return roadmaps;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addRoadmap(RoadmapModel roadmap) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_ADD_ROADMAP);
            stmt.setString(1, roadmap.getTitle());
            stmt.setString(2, roadmap.getDescription());
            stmt.setString(3, roadmap.getSkillLevel());
            // Assuming imagePath is optional and can be null
            stmt.setString(4, roadmap.getImagePath() != null ? roadmap.getImagePath() : "default_path");

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This method is used to get the total count of users in the database.
     * 
     * @return An integer value representing the total count of users in the
     *         database.
     */
    public int getUserCount() {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_GET_USER_COUNT);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return result.getInt(1);
            }
            return 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * This method is used to delete a user's information from the database. It
     * takes a
     * username as input and returns an integer value based on the result of the
     * deletion.
     * 
     * @param username The username of the user to be deleted.
     * @return An integer value representing the number of rows affected by the
     *         deletion.
     */
    public int deleteUserInfo(String username) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_DELETE_USER);
            stmt.setString(1, username);
            return stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int deleteRoadmap(String roadmapId) {
        try {
            String query = "DELETE FROM roadmaps WHERE roadmap_id = ?";
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, roadmapId);
            return stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public RoadmapModel getRoadmapInfo(String roadmapId) {
        try {
            String query = "SELECT * FROM roadmaps WHERE roadmap_id = ?";
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, roadmapId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                RoadmapModel roadmap = new RoadmapModel();
                roadmap.setRoadmapId(rs.getInt("roadmap_id"));
                roadmap.setTitle(rs.getString("title"));
                roadmap.setDescription(rs.getString("description"));
                roadmap.setSkillLevel(rs.getString("skill_level"));
                roadmap.setImagePath(rs.getString("image_path"));
                return roadmap;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * This method is used to update a user's information in the database. It takes
     * a
     * UserModel object as input and returns a boolean value based on the result of
     * the update.
     * 
     * @param user The UserModel object containing the updated user's information.
     * @return A boolean value: true if the update was successful, false otherwise.
     */
    public boolean updateUserInfo(UserModel user) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_UPDATE_USER);
            stmt.setString(1, user.getFirst_name());
            stmt.setString(2, user.getLast_name());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhoneNumber());
            stmt.setString(5, user.getUsername());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This method is used to retrieve a user's information from the database. It
     * takes a
     * username as input and returns a UserModel object representing the user's
     * information.
     * 
     * @param username The username of the user.
     * @return A UserModel object representing the user's information.
     */
    public UserModel getUserInfo(String username) {
        UserModel user = null;
        try {
            PreparedStatement stmt = getConnection().prepareStatement(StringUtils.QUERY_GET_USER);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new UserModel();
                user.setUsername(rs.getString("username"));
                user.setFirst_name(rs.getString("first_name"));
                user.setLast_name(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setIs_admin(rs.getBoolean("is_admin"));
                user.setPhoneNumber(rs.getString("phonenumber"));
                user.setProfilePicturePath(rs.getString("profile_picture"));
                // add other fields as necessary
            }
            rs.close();
            stmt.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * This method is used to retrieve all roadmap models associated with a user. It
     * takes a
     * username as input and returns an ArrayList of RoadmapModel objects.
     * 
     * @param username The username of the user.
     * @return An ArrayList of RoadmapModel objects, each representing a roadmap
     *         associated with the user.
     */
    public ArrayList<RoadmapModel> getRoadmapModels(String username) {
        ArrayList<RoadmapModel> roadmaps = new ArrayList<>();
        try {
            String query = "SELECT r.* FROM Roadmaps r " +
                    "JOIN UserRoadmap ur ON r.roadmap_id = ur.roadmap_id " +
                    "JOIN Users u ON ur.user_id = u.user_id " +
                    "WHERE u.username = ?";

            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                RoadmapModel roadmap = new RoadmapModel();
                roadmap.setRoadmapId(rs.getInt("roadmap_id"));
                roadmap.setTitle(rs.getString("title"));
                roadmap.setDescription(rs.getString("description"));
                roadmap.setSkillLevel(rs.getString("skill_level"));
                roadmap.setImagePath(rs.getString("image_path"));
                roadmaps.add(roadmap);
            }

            rs.close();
            stmt.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return roadmaps;
    }

    /**
     * This method is used to retrieve a user's information from the database using
     * their username.
     * It takes a username as input and returns a UserModel object representing the
     * user's information.
     * 
     * @param username The username of the user.
     * @return A UserModel object representing the user's information.
     */
    public UserModel getUserByUsername(String username) {
        UserModel user = null;
        try {
            String query = "SELECT * FROM Users WHERE username = ?";

            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new UserModel();
                user.setUser_id(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setFirst_name(rs.getString("first_name"));
                user.setLast_name(rs.getString("last_name"));
                // Set any other fields on the user model here
            }

            rs.close();
            stmt.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * This method is used to enroll a user in a roadmap. It takes a userId and a
     * roadmapID as input.
     * 
     * @param userId    The ID of the user.
     * @param roadmapID The ID of the roadmap.
     */
    public void enrollUserInRoadmap(String userId, String roadmapID) {
        if (userId == null || roadmapID == null) {
            throw new IllegalArgumentException("userId and roadmapID must not be null");
        }
        try {
            String query = "INSERT INTO userroadmap (user_id, roadmap_id) VALUES (?, ?)";

            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, userId);
            stmt.setString(2, roadmapID);
            stmt.executeUpdate();

            stmt.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

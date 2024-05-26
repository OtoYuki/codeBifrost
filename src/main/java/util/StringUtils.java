package util;

import javax.print.DocFlavor.STRING;

public class StringUtils {
    // Starting the Database Connection
    public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String LOCALHOST_URL = "jdbc:mysql://localhost:3306/codebifrost";
    public static final String LOCALHOST_USERNAME = "root";
    public static final String LOCALHOST_PASSWORD = "";
    // End Database Connection

    // Start SQL Queries
    public static final String QUERY_REGISTER_USER = "INSERT INTO users ("
            + "user_id, username, first_name, last_name, email, password, is_admin, phonenumber, profile_picture)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String QUERY_LOGIN_USER_CHECK = "SELECT * FROM users WHERE username = ?";
    public static final String QUERY_ADMIN_CHECK = "SELECT * FROM users WHERE username = ? AND is_admin = 1";
    public static final String QUERY_GET_ALL_USERS = "SELECT * FROM users";
    public static final String QUERY_GET_USER_COUNT = "SELECT COUNT(*) FROM users";
    public static final String QUERY_DELETE_USER = "DELETE FROM users WHERE username = ?";
    public static final String QUERY_UPDATE_USER = "UPDATE users SET first_name = ?, last_name = ?, email = ?, phonenumber = ? WHERE username = ?";
    public static final String QUERY_GET_USER = "SELECT * FROM users WHERE username = ?";
    public static final String QUERY_GET_ALL_ROADMAPS = "SELECT * FROM roadmaps";
    public static final String QUERY_ADD_ROADMAP = "INSERT INTO roadmaps (title, description, skill_level, image_path) VALUES (?, ?, ?, ?)";
    // End SQL Queries

    // Start Parameter Names
    public static final String USERID = "userId";
    public static final String USERNAME = "username";
    public static final String FIRSTNAME = "firstName";
    public static final String LASTNAME = "lastName";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String ISADMIN = "isAdmin";
    public static final String PHONENUMBER = "phoneNumber";
    public static final String PROFILEPICTURE = "profilePicture";
    // End Parameter Names

    // Start Messages
    public static final String MESSAGE_SUCCESS_UPDATE = "Successfully Updated!";
    public static final String MESSAGE_ERROR_UPDATE = "Update Failed. Please try again.";

    // Start: Validation Messages
    // Register Page Messages
    public static final String MESSAGE_SUCCESS_REGISTER = "Successfully Registered!";
    public static final String MESSAGE_ERROR_REGISTER = "Please correct the form data.";
    public static final String MESSAGE_ERROR_USERNAME = "Username is already registered.";
    public static final String MESSAGE_ERROR_EMAIL = "Email is already registered.";
    public static final String MESSAGE_ERROR_PHONE_NUMBER = "Phone number is already registered.";
    public static final String MESSAGE_ERROR_PASSWORD_UNMATCHED = "Password is not matched.";
    public static final String MESSAGE_ERROR_INCORRECT_DATA = "Please correct all the fields.";

    // Login Page Messages
    public static final String MESSAGE_SUCCESS_LOGIN = "Successfully LoggedIn!";
    public static final String MESSAGE_ERROR_LOGIN = "Either username or password is not correct!";
    public static final String MESSAGE_ERROR_CREATE_ACCOUNT = "Account for this username is not registered! Please create a new account.";

    // Other Messages
    public static final String MESSAGE_ERROR_SERVER = "An unexpected server error occurred.";
    public static final String MESSAGE_SUCCESS_DELETE = "Successfully Deleted!";
    public static final String MESSAGE_ERROR_DELETE = "Cannot delete the user!";

    public static final String MESSAGE_SUCCESS = "successMessage";
    public static final String MESSAGE_ERROR = "errorMessage";
    // End: Validation Messages

    // Start Servlet Routes
    public static final String SERVLET_URL_LOGIN = "/loginuser";
    public static final String SERVLET_URL_REGISTER = "/registeruser";
    public static final String SERVLET_URL_ADMIN = "/admin";
    public static final String SERVLET_URL_MODIFY_USER = "/modifyuser";
    public static final String SERVLET_URL_UPDATE_USER = "/updateuser";
    public static final String SERVLET_URL_RESOURCES = "/resourcesservlet";
    public static final String SERVLET_URL_HOME = "/home";
    public static final String SERVLET_URL_PROFILE = "/profile";
    public static final String SERVLET_URL_ENROLL = "/enroll";
    public static final String SERVLET_URL_ADD_ROADMAP = "/addroadmap";
    public static final String SERVLET_URL_MODIFY_ROADMAP = "/modifyroadmap";
    // End Servlet Routes

    // Start JSP Routes
    public static final String PAGE_URL_INDEX = "index.jsp";
    public static final String PAGE_URL_HOME = "/pages/userhome.jsp";
    public static final String PAGE_URL_HEADER = "/pages/header.jsp";
    public static final String PAGE_URL_NAV = "/pages/nav.jsp";
    public static final String PAGE_URL_LOGIN = "/pages/login.jsp";
    public static final String PAGE_URL_REGISTER = "/pages/register.jsp";
    public static final String PAGE_URL_ADMIN = "/pages/admindashboard.jsp";
    public static final String PAGE_URL_USER = "/pages/userhome.jsp";
    public static final String PAGE_URL_UPDATE = "/pages/update.jsp";
    public static final String PAGE_URL_RESOURCES = "/pages/resources.jsp";
    public static final String PAGE_URL_PROFILE = "/pages/profile.jsp";
    // End JSP Routes

    // Normal Text
    public static final String USER_COUNT = "totalUsers";
    public static final String USER_LIST = "userList";
    public static final String DELETE_ID = "deleteId";
    public static final String UPDATE_ID = "updateId";
    public static final String ROADMAP_LIST = "roadmapList";

    // Image Directory Path
    public static final String RESOURCE_IMG_DIR = "/resources/images/resource_img";
}

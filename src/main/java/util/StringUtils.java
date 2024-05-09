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
            + "user_id, username, first_name, last_name, email, password, is_admin, phonenumber)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String QUERY_LOGIN_USER_CHECK = "SELECT * FROM users WHERE username = ?";
    public static final String QUERY_ADMIN_CHECK = "SELECT * FROM users WHERE username = ? AND is_admin = 1";
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
    // End Parameter Names

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
    public static final String SERVLET_URL_REGISTER = "/register";
    // End Servlet Routes

    // Start JSP Routes
    public static final String PAGE_URL_HOME = "/pages/userhome.jsp";
    public static final String PAGE_URL_HEADER = "/pages/header.jsp";
    public static final String PAGE_URL_NAV = "/pages/nav.jsp";
    public static final String PAGE_URL_LOGIN = "/pages/login.jsp";
    public static final String PAGE_URL_REGISTER = "/pages/register.jsp";
    public static final String PAGE_URL_ADMIN = "/pages/admindashboard.jsp";
    public static final String PAGE_URL_USER = "/pages/userhome.jsp";
    // End JSP Routes
}

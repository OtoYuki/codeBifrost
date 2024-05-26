package controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.database.DatabaseController;
import model.LoginModel;
import util.StringUtils;

@WebServlet(urlPatterns = StringUtils.SERVLET_URL_LOGIN, asyncSupported = true)
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final DatabaseController databaseController;

    public LoginServlet() {
        this.databaseController = new DatabaseController();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Extract the username and password from the request
        String userName = request.getParameter(StringUtils.USERNAME);
        String password = request.getParameter(StringUtils.PASSWORD);

        // Creating a LoginModel object to hold the username and password
        LoginModel loginModel = new LoginModel(userName, password);

        // Call the DatabaseController to validate the user credentials
        int loginResult = databaseController.getStudentLoginInfo(loginModel);

        // Call the DatabaseController to validate the user credentials
        if (loginResult == 1) {
            // Login successful
            HttpSession userSession = request.getSession();
            userSession.setAttribute(StringUtils.USERNAME, userName);
            userSession.setMaxInactiveInterval(30 * 60);

            Cookie userCookie = new Cookie(StringUtils.USERNAME, userName);
            userCookie.setMaxAge(30 * 60);
            response.addCookie(userCookie);

            request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_LOGIN);
            // Check if the user is an admin
            if (databaseController.isAdmin(userName)) {
                System.out.println("Admin Login");
                request.getRequestDispatcher(StringUtils.SERVLET_URL_ADMIN).forward(request, response);
            } else {
                System.out.println("User Login");
                request.getRequestDispatcher(StringUtils.SERVLET_URL_HOME).forward(request, response);
            }
        } else if (loginResult == 0) {
            // Username or Password Missmatch
            System.out.println("Username or Password Missmatch");
            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_LOGIN);
            request.setAttribute(StringUtils.USERNAME, userName);
            request.getRequestDispatcher(StringUtils.PAGE_URL_HEADER).forward(request, response);
        } else if (loginResult == -1) {
            // Username not Found
            System.out.println("Username not Found");
            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_CREATE_ACCOUNT);
            request.setAttribute(StringUtils.USERNAME, userName);
            request.getRequestDispatcher(StringUtils.PAGE_URL_HEADER).forward(request, response);
        } else {
            // Server Error
            System.out.println("Server Error");
            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
            request.setAttribute(StringUtils.USERNAME, userName);
            request.getRequestDispatcher(StringUtils.PAGE_URL_HEADER).forward(request, response);
        }
    }
}

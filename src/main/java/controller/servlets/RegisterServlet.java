package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DatabaseController;
import model.UserModel;
import util.StringUtils;

/**
 * This servlet class handles the requests for registration of new users. It
 * extracts the user data from the registration form, performs basic validation,
 * and attempts to register the new user in the database using a
 * `DatabaseController`. The user is then redirected to their login page upon
 * successful registraction, or back to the registration page with an error.
 * 
 * @author Sushant Hona (serotoninotonin@gmail.com)
 * @version 1.0
 * @since 2024-05-01
 */

@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_REGISTER })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DatabaseController databaseController;

	public RegisterServlet() {
		this.databaseController = new DatabaseController();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter(StringUtils.USERNAME);
		System.out.println("Username: " + username);
		String firstName = request.getParameter(StringUtils.FIRSTNAME);
		System.out.println("First Name: " + firstName);
		String lastName = request.getParameter(StringUtils.LASTNAME);
		System.out.println("Last Name: " + lastName);
		String email = request.getParameter(StringUtils.EMAIL);
		System.out.println("Email: " + email);
		String password = request.getParameter(StringUtils.PASSWORD);
		System.out.println("Password: " + password);
		String phoneNumber = request.getParameter(StringUtils.PHONENUMBER);
		System.out.println("Phone Number: " + phoneNumber);

		// Create a UserModel object with the extracted data
		UserModel user = new UserModel(0, username, email, password, firstName, lastName, false, phoneNumber);

		// Data Validation

		// Call DatabaseController to register the user
		int result = databaseController.registerUser(user);
		System.out.println("Result: " + result);

		// Check the result of the registration
		// Check the result of the registration
		if (result == 1) {
			// Registration Successful
			response.getWriter().write(StringUtils.MESSAGE_SUCCESS_REGISTER);
		} else if (result == 0) {
			// Registration Failed
			response.getWriter().write(StringUtils.MESSAGE_ERROR_REGISTER);
		} else {
			// Exception Occurred
			response.getWriter().write(StringUtils.MESSAGE_ERROR_SERVER);
		}
	}

}

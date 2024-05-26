package controller.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

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

@WebServlet(urlPatterns = { StringUtils.SERVLET_URL_REGISTER }, asyncSupported = true)
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
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
		request.setCharacterEncoding("UTF-8");

		// Extract user data from the request
		String username = IOUtils.toString(request.getPart(StringUtils.USERNAME).getInputStream(),
				StandardCharsets.UTF_8);
		System.out.println("Username: " + username);
		String firstName = IOUtils.toString(request.getPart(StringUtils.FIRSTNAME).getInputStream(),
				StandardCharsets.UTF_8);
		System.out.println("First Name: " + firstName);
		String lastName = IOUtils.toString(request.getPart(StringUtils.LASTNAME).getInputStream(),
				StandardCharsets.UTF_8);
		System.out.println("Last Name: " + lastName);
		String email = IOUtils.toString(request.getPart(StringUtils.EMAIL).getInputStream(), StandardCharsets.UTF_8);
		System.out.println("Email: " + email);
		String password = IOUtils.toString(request.getPart(StringUtils.PASSWORD).getInputStream(),
				StandardCharsets.UTF_8);
		System.out.println("Password: " + password);
		String phoneNumber = IOUtils.toString(request.getPart(StringUtils.PHONENUMBER).getInputStream(),
				StandardCharsets.UTF_8);
		System.out.println("Phone Number: " + phoneNumber);
		String profilePicturePath = IOUtils.toString(request.getPart(StringUtils.PROFILEPICTURE).getInputStream(),
				StandardCharsets.UTF_8);

		// Create a UserModel object with the extracted data
		UserModel user = new UserModel(0, username, email, password, firstName, lastName, false, phoneNumber,
				profilePicturePath);

		// Data Validation

		// Call DatabaseController to register the user
		int result = databaseController.registerUser(user);
		System.out.println("Result: " + result);

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

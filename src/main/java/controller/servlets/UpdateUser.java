package controller.servlets;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DatabaseController;
import model.UserModel;
import util.StringUtils;

/**
 * Servlet implementation class UpdateUser
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_UPDATE_USER })
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateUser() {
		super();
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		UserModel user = new UserModel();
		user.setUsername(request.getParameter("username"));
		user.setFirst_name(request.getParameter("firstName"));
		System.out.println(request.getParameter("firstName"));
		user.setLast_name(request.getParameter("lastName"));
		user.setEmail(request.getParameter("email"));
		user.setPhoneNumber(request.getParameter("phoneNumber"));
		user.setPassword(request.getParameter("password"));

		DatabaseController databaseController = new DatabaseController();
		boolean isUpdated = databaseController.updateUserInfo(user);

		if (isUpdated) {
			// Get the username from the session
			String username = (String) request.getSession().getAttribute("username");
			// Check if the user is an admin
			boolean isAdmin = databaseController.isAdmin(username);
			if (isAdmin) {
				response.sendRedirect(request.getContextPath() + StringUtils.SERVLET_URL_ADMIN);
			} else {
				response.sendRedirect(request.getContextPath() + StringUtils.SERVLET_URL_PROFILE);
			}
		} else {
			// Set the error message in the session
			String errorMessage = "Invalid Inputs";
			request.getSession().setAttribute("errorMessage", errorMessage);
			// Get the username from the session
			String username = (String) request.getSession().getAttribute("username");
			// Check if the user is an admin
			boolean isAdmin = databaseController.isAdmin(username);
			String redirectUrl = isAdmin ? StringUtils.SERVLET_URL_ADMIN : StringUtils.SERVLET_URL_PROFILE;
			// Include the error message in the URL
			response.sendRedirect(
					request.getContextPath() + redirectUrl + "?error=" + URLEncoder.encode(errorMessage, "UTF-8"));
		}
	}

}

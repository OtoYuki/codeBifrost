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
 * Servlet implementation class ModifyUser
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_MODIFY_USER })
public class ModifyUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DatabaseController databaseController;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyUser() {
		super();
		// TODO Auto-generated constructor stub
		this.databaseController = new DatabaseController();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String updateId = request.getParameter("updateId");
		String deleteId = request.getParameter("deleteId");

		if (updateId != null && !updateId.isEmpty()) {
			UserModel user = databaseController.getUserInfo(updateId);
			if (user != null) {
				request.setAttribute("user", user);
				request.getRequestDispatcher(StringUtils.PAGE_URL_UPDATE).forward(request, response);
			} else {
				// handle error
			}
		}
		if (deleteId != null && !deleteId.isEmpty()) {
			doDelete(request, response);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("DELETE");
		if (databaseController.deleteUserInfo(req.getParameter(StringUtils.DELETE_ID)) == 1) {
			System.out.println("User Deleted");
			req.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_DELETE);
			req.getRequestDispatcher(StringUtils.SERVLET_URL_ADMIN).forward(req, resp);
		} else {
			System.out.println("User Not Deleted");
			req.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_DELETE);
			req.getRequestDispatcher(StringUtils.SERVLET_URL_ADMIN).forward(req, resp);
		}
	}

}

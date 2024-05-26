package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DatabaseController;
import model.RoadmapModel;
import model.UserModel;
import util.StringUtils;

/**
 * Servlet implementation class AdminDashboard
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_ADMIN })
public class AdminDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseController controller = new DatabaseController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminDashboardServlet() {
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
		int countUsers = controller.getUserCount();
		ArrayList<UserModel> users = controller.getAllUsersInfo();
		ArrayList<RoadmapModel> roadmaps = controller.getAllRoadmapModels();
		System.out.println(users);
		System.out.println(countUsers);
		request.setAttribute(StringUtils.USER_COUNT, countUsers);
		request.setAttribute(StringUtils.USER_LIST, users);
		request.setAttribute(StringUtils.ROADMAP_LIST, roadmaps);
		request.getRequestDispatcher(StringUtils.PAGE_URL_ADMIN).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

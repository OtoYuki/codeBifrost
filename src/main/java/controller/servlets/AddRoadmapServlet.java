package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DatabaseController;
import model.RoadmapModel;
import util.StringUtils;

/**
 * Servlet implementation class AddRoadmapServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_ADD_ROADMAP })
public class AddRoadmapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseController controller = new DatabaseController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddRoadmapServlet() {
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
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String skill_level = request.getParameter("skill_level");

		RoadmapModel roadmap = new RoadmapModel();
		roadmap.setTitle(title);
		roadmap.setDescription(description);
		roadmap.setSkillLevel(skill_level);

		DatabaseController databaseController = new DatabaseController();
		databaseController.addRoadmap(roadmap);

		response.sendRedirect(request.getContextPath() + StringUtils.SERVLET_URL_ADMIN);
	}

}

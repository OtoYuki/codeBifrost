package controller.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.database.DatabaseController;
import model.RoadmapModel;
import util.StringUtils;

/**
 * Servlet implementation class ResourcesServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_RESOURCES })
public class ResourcesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Database Connection
	DatabaseController databaseController = new DatabaseController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResourcesServlet() {
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
		try {
			// Get a connection to the database
			Connection connection = databaseController.getConnection();

			// Execute a SQL query to get the roadmaps
			String sql = "SELECT * FROM `roadmaps`";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			// Convert the result set to a list of roadmaps
			List<RoadmapModel> roadmaps = new ArrayList<>();
			while (resultSet.next()) {
				RoadmapModel roadmap = new RoadmapModel();
				roadmap.setTitle(resultSet.getString("title"));
				roadmap.setDescription(resultSet.getString("description"));
				roadmap.setSkillLevel(resultSet.getString("skill_level"));
				roadmap.setImagePath(resultSet.getString("image_path"));
				roadmaps.add(roadmap);
			}

			// Add the list of roadmaps to the request
			request.setAttribute("roadmaps", roadmaps);

			// Forward the request to the JSP
			request.getRequestDispatcher(StringUtils.PAGE_URL_RESOURCES).forward(request, response);
		} catch (SQLException e) {
			throw new ServletException(e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

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
 * Servlet implementation class ModifyRoadmapsServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_MODIFY_ROADMAP })
public class ModifyRoadmapsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyRoadmapsServlet() {
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
		String updateId = request.getParameter(StringUtils.UPDATE_ID);
		String deleteId = request.getParameter(StringUtils.DELETE_ID);

		DatabaseController databaseController = new DatabaseController();

		if (updateId != null && !updateId.isEmpty()) {
			RoadmapModel roadmap = databaseController.getRoadmapInfo(updateId);
			if (roadmap != null) {
				request.setAttribute("roadmap", roadmap);
				request.getRequestDispatcher(StringUtils.PAGE_URL_UPDATE).forward(request, response);
			} else {
			}
		}
		if (deleteId != null && !deleteId.isEmpty()) {
			doDelete(request, response);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String deleteId = request.getParameter(StringUtils.DELETE_ID);
		DatabaseController databaseController = new DatabaseController();
		int isDeleted = databaseController.deleteRoadmap(deleteId);

		if (isDeleted > 0) {
			response.sendRedirect(request.getContextPath() + StringUtils.SERVLET_URL_ADMIN);
		} else {
			response.sendRedirect(request.getContextPath() + StringUtils.SERVLET_URL_MODIFY_ROADMAP);
		}
	}

}

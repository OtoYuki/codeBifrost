<%@page import="util.StringUtils" %><%@ page language="java"
contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <<head>
    <meta charset="UTF-8" />
    <title>Admin Dashboard</title>
    <link
      href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="<%=request.getContextPath()%>/stylesheets/admindashboard.css"
    />
  </head>
  <body>
    <!-- partial:index.partial.html -->
    <jsp:include page="<%=StringUtils.PAGE_URL_HEADER%>" />
    <div class="all">
      <jsp:include page="<%=StringUtils.PAGE_URL_NAV%>" />
      <div class="content">
        <div class="greeting">
          <h2>Hello Admin!</h2>
          <p>What would you like to do? 🌞</p>
        </div>

        <main>
          <div class="grid-area-1">
            <div class="distance">
              <section class="distance__section distance__cycling">
                <p><c:out value="${totalUsers}" /></p>
                <h2>Total Users</h2>
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                  <path
                    fill="currentColor"
                    d="M11 15.414V20h2v-4.586c0-.526-.214-1.042-.586-1.414l-2-2L13 9.414l2 2c.372.372.888.586 1.414.586H20v-2h-3.586l-3.707-3.707a.999.999 0 0 0-1.414 0L8 9.586c-.378.378-.586.88-.586 1.414s.208 1.036.586 1.414l3 3z"
                  />
                  <circle cx="16" cy="5" r="2" fill="currentColor" />
                  <path
                    fill="currentColor"
                    d="M18 14c-2.206 0-4 1.794-4 4s1.794 4 4 4s4-1.794 4-4s-1.794-4-4-4zm0 6c-1.103 0-2-.897-2-2s.897-2 2-2s2 .897 2 2s-.897 2-2 2zM6 22c2.206 0 4-1.794 4-4s-1.794-4-4-4s-4 1.794-4 4s1.794 4 4 4zm0-6c1.103 0 2 .897 2 2s-.897 2-2 2s-2-.897-2-2s.897-2 2-2z"
                  />
                </svg>
              </section>
            </div>

            <div class="grid-area-1-2">
              <section class="recent">
                <h2>Users List</h2>
                <table>
                  <tr>
                    <th>Username</th>
                    <th>Actions</th>
                  </tr>
                  <c:forEach var="user" items="${userList}">
                    <tr>
                      <td>${user.username}</td>
                      <td>
                        <form
                          action="<%=request.getContextPath() + StringUtils.SERVLET_URL_MODIFY_USER%>"
                          method="post"
                        >
                          <input
                            type="hidden"
                            name="<%=StringUtils.UPDATE_ID %>"
                            value="${user.username}"
                          />
                          <button type="submit">Update</button>
                        </form>
                        <form
                          id="deleteForm-${user.username}"
                          action="<%=request.getContextPath() + StringUtils.SERVLET_URL_MODIFY_USER%>"
                          method="post"
                        >
                          <input
                            type="hidden"
                            name="<%=StringUtils.DELETE_ID%>"
                            value="${user.username}"
                          />
                          <button
                            type="button"
                            onclick="confirmDelete('${user.username}')"
                          >
                            Delete
                          </button>
                        </form>
                      </td>
                    </tr>
                  </c:forEach>
                </table>
              </section>
            </div>
            <div class="grid-area-2-1">
              <section class="recent">
                <h2>Roadmaps List</h2>
                <table>
                  <tr>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Image</th>
                    <th>Actions</th>
                  </tr>
                  <c:forEach var="roadmap" items="${roadmapList}">
                    <tr>
                      <td>${roadmap.title}</td>
                      <td>${roadmap.description}</td>
                      <td>
                        <img src="<c:out
                          value="${pageContext.request.contextPath}"
                        />${roadmap.imagePath}" alt="Roadmap Image"
                        style="width: 100px; height: 100px;">
                      </td>
                      <td>
                        <form
                          id="deleteRoadmapForm-${roadmap.roadmapId}"
                          action="<%=request.getContextPath() + StringUtils.SERVLET_URL_MODIFY_ROADMAP%>"
                          method="post"
                        >
                          <input
                            type="hidden"
                            name="<%=StringUtils.DELETE_ID%>"
                            value="${roadmap.roadmapId}"
                          />
                          <button
                            type="button"
                            onclick="confirmDeleteRoadmap('${roadmap.roadmapId}')"
                          >
                            Delete
                          </button>
                        </form>
                      </td>
                    </tr>
                  </c:forEach>
                </table>
                <button id="addRoadmapButton">Add Roadmap</button>

                <div id="addRoadmapForm" style="display: none">
                  <form
                    action="<%=request.getContextPath() + StringUtils.SERVLET_URL_ADD_ROADMAP%>"
                    method="post"
                  >
                    <label for="title">Title:</label><br />
                    <input type="text" id="title" name="title" /><br />
                    <label for="description">Description:</label><br />
                    <textarea id="description" name="description"></textarea
                    ><br />
                    <label for="skill_level">Skill Level:</label><br />
                    <select id="skill_level" name="skill_level">
                      <option value="beginner">Beginner</option>
                      <option value="intermediate">Intermediate</option>
                      <option value="advanced">Advanced</option></select
                    ><br />
                    <input type="submit" value="Submit" />
                  </form>
                </div>
              </section>
            </div>
          </div>
        </main>
      </div>
    </div>
    <!-- partial -->
  </body>
  <script>
    function confirmDelete(username) {
      if (confirm("Are you sure you want to delete this user?")) {
        document.getElementById("deleteForm-" + username).submit();
      }
    }
    document
      .getElementById("addRoadmapButton")
      .addEventListener("click", function () {
        document.getElementById("addRoadmapForm").style.display = "block";
      });

    function confirmDeleteRoadmap(roadmapId) {
      if (confirm("Are you sure you want to delete this roadmap?")) {
        document.getElementById("deleteRoadmapForm-" + roadmapId).submit();
      }
    }
    window.onload = function () {
      // Get the URL parameters
      var params = new URLSearchParams(window.location.search);
      // Check if there is an error message
      if (params.has("error")) {
        // Display the error message
        alert(params.get("error"));
      }
    };
  </script>
</html>

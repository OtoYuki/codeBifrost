<%@page import="util.StringUtils" %><%@ page language="java"
contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@page
import="model.UserModel" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Profile</title>
    <link
      rel="stylesheet"
      type="text/css"
      href="<%=request.getContextPath()%>/stylesheets/profile.css"
    />
  </head>
  <body>
    <% UserModel user = (UserModel) request.getAttribute("user"); if (user ==
    null) { response.sendRedirect(request.getContextPath() +
    "/pages/login.jsp"); return; } %>
    <jsp:include page="<%=StringUtils.PAGE_URL_HEADER%>" />
    <jsp:include page="<%=StringUtils.PAGE_URL_NAV%>" />

    <div class="profile-container">
      <h2>User Profile</h2>
      <p><strong>Username:</strong> <%= user.getUsername() %></p>
      <p><strong>Email:</strong> <%= user.getEmail() %></p>
      <p><strong>First Name:</strong> <%= user.getFirst_name() %></p>
      <p><strong>Last Name:</strong> <%= user.getLast_name() %></p>
      <form
        action="<%=request.getContextPath() + StringUtils.SERVLET_URL_PROFILE%>"
        method="post"
      >
        <input type="submit" value="Logout" />
      </form>
      <form
        action="<%=request.getContextPath() + StringUtils.SERVLET_URL_MODIFY_USER%>"
        method="post"
      >
        <input
          type="hidden"
          name="<%=StringUtils.UPDATE_ID %>"
          value="${user.username}"
        />
        <button
          type="submit"
          style="
            cursor: pointer;
            padding: 10px 20px;
            font-size: 0.65rem;
            font-weight: bold;
            letter-spacing: 0.025rem;
            text-transform: uppercase;
            color: white;
            background-color: rgba(255, 255, 255, 0.1);
            border: 1px solid rgba(255, 255, 255, 0.2);
            backdrop-filter: blur(10px);
            box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37);
            border-radius: 10px;
            transition: all 0.3s ease;
            margin-right: 10px; /* Add some space between the buttons */
          "
        >
          Update
        </button>
      </form>
    </div>
  </body>
  <script>
    function showUpdateMessage() {
      alert("Contact +9779840147687 to update your details");
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

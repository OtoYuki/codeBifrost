<%@page import="util.StringUtils" %> <%@ page language="java"
contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <link
      rel="stylesheet"
      type="text/css"
      a
      href="<%=request.getContextPath()%>/stylesheets/resources.css"
    />
    <script>
      function showAlert() {
        // Get the username from the session
        var username = '<%= session.getAttribute("username") %>';
    
        // Check if the user is logged in
        if (username == null || username == '' || username == 'null') {
          // If the user is not logged in, redirect to the login page
          alert('Please log in first');
        } else {
          // If the user is logged in, display the message
          alert('Admin Will Get Back to you Shortly');
        }
      }
    </script>
  </head>
  <body>
    <jsp:include page="<%=StringUtils.PAGE_URL_HEADER%>" />
    <jsp:include page="<%=StringUtils.PAGE_URL_NAV%>" />
    <!-- partial:index.partial.html -->
    <main class="page-content">
      <c:forEach var="roadmap" items="${roadmaps}">
        <div class="card" style="background-image: url('<c:out value="${pageContext.request.contextPath}"/>${roadmap.imagePath}')">
          <div class="content">
            <h2 class="title">${roadmap.title}</h2>
            <p class="copy">${roadmap.description}</p>
            <input
              type="button"
              class="btn"
              value="Enroll"
              onclick="showAlert()"
            />
          </div>
        </div>
      </c:forEach>
    </main>
    <!-- partial -->
  </body>
</html>

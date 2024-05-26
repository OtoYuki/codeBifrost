<%@page import="util.StringUtils" %> <%@ page language="java"
contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <% String username =
(String) session.getAttribute(StringUtils.USERNAME); if (username == null) {
response.sendRedirect(request.getContextPath() + "/index.jsp"); return; } %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Home</title>
    <link
      rel="stylesheet"
      type="text/css"
      href="<%=request.getContextPath()%>/stylesheets/userhome.css"
    />
  </head>
  <body>
    <jsp:include page="<%=StringUtils.PAGE_URL_HEADER%>" />
    <jsp:include page="<%=StringUtils.PAGE_URL_NAV%>" />

    <h1>Your Enrolled Roadmaps:</h1>
    <div class="roadmaps">
      <c:if test="${empty roadmaps}">
        <p>You have not enrolled in any roadmaps</p>
      </c:if>
      <c:forEach var="roadmap" items="${roadmaps}">
        <div class="roadmap-card">
          <h2>${roadmap.title}</h2>
          <p>${roadmap.description}</p>
        </div>
      </c:forEach>
    </div>
  </body>
</html>

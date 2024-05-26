<%@page import="util.StringUtils" %><%@ page language="java"
contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      rel="stylesheet"
      type="text/css"
      href="<%=request.getContextPath()%>/stylesheets/nav.css"
    />
    <title>Nav</title>
  </head>
  <body>
    <nav>
      <ul>
        <li>
          <a
            href="<%=request.getContextPath() + StringUtils.SERVLET_URL_HOME%> "
            >Home</a
          >
        </li>
        <li>
          <a
            href="<%=request.getContextPath() + StringUtils.SERVLET_URL_RESOURCES%>"
            >Roadmaps</a
          >
        </li>
        <li>
          <a href="<%=request.getContextPath()  + StringUtils.PAGE_URL_LOGIN%>"
            >Login</a
          >
        </li>
        <li>
          <a
            href="<%=request.getContextPath()  + StringUtils.SERVLET_URL_PROFILE%>"
            >Profile</a
          >
        </li>
      </ul>
    </nav>
  </body>
</html>

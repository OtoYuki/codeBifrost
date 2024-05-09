<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
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
      <a href="#">Home</a>
      <a href="#">Menu</a>
      <a href="#" class="current">Gallery</a>
      <a href="#">About</a>
      <a href="#">Contact</a>
      <div class="nav-underline"></div>
      <div class="nav-underline2"></div>
    </nav>
  </body>
</html>

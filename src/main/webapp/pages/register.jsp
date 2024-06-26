<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Register</title>
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Noto+Sans:400,700"
    />
    <link
      rel="stylesheet"
      href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css"
    />
    <link rel="stylesheet" href="../stylesheets/register.css" />
  </head>

  <body>
    <!-- partial:index.partial.html -->
    <div id="progress"></div>

    <div class="center">
      <div id="register">
        <i id="previousButton" class="ion-android-arrow-back"></i>
        <i id="forwardButton" class="ion-android-arrow-forward"></i>

        <div id="inputContainer">
          <input id="inputField" required multiple />
          <label id="inputLabel"></label>
          <div id="inputProgress"></div>
        </div>
      </div>
    </div>
    <!-- partial -->
    <script>
      var contextPath = "<%=request.getContextPath()%>";
    </script>
    <script src="../js/register.js"></script>
  </body>
</html>

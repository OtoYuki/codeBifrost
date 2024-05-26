<%@page import="util.StringUtils" %> <%@ page language="java"
contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Login</title>
    <link
      rel="stylesheet"
      type="text/css"
      href="<%=request.getContextPath()%>/stylesheets/update.css"
    />
  </head>
  <body>
    <jsp:include page="<%=StringUtils.PAGE_URL_HEADER%>" />
    <jsp:include page="<%=StringUtils.PAGE_URL_NAV%>" />
    <!--ring div starts here-->
    <div class="ring">
      <i style="--clr: #aec6cf"></i>
      <!-- Pastel Blue -->
      <i style="--clr: #fdecb3"></i>
      <!-- Pastel Yellow -->
      <i style="--clr: #ffb3de"></i>
      <!-- Pastel Pink -->
      <div class="login">
        <h2>Update</h2>
        <form
          action="<%=request.getContextPath() + StringUtils.SERVLET_URL_UPDATE_USER%>"
          method="post"
          onsubmit="return validateForm()"
        >
          <div class="inputBx">
            <input
              type="text"
              name="username"
              placeholder="Username"
              value="${user.username}"
            />
          </div>
          <div class="inputBx">
            <input
              type="text"
              name="firstName"
              placeholder="First Name"
              value="${user.first_name}"
            />
          </div>
          <div class="inputBx">
            <input
              type="text"
              name="lastName"
              placeholder="Last Name"
              value="${user.last_name}"
            />
          </div>
          <div class="inputBx">
            <input
              type="text"
              name="email"
              placeholder="Email"
              value="${user.email}"
            />
          </div>
          <div class="inputBx">
            <input
              type="text"
              name="phoneNumber"
              placeholder="Phone Number"
              value="${user.phoneNumber}"
            />
          </div>
          <div class="inputBx">
            <input type="submit" value="Update" />
          </div>
        </form>
        <div class="links">
          <a href="#">Forget Password</a>
          <a
            href="<%=request.getContextPath() + StringUtils.PAGE_URL_REGISTER%>"
            >Signup</a
          >
        </div>
      </div>
    </div>
    <!--ring div ends here-->
  </body>
  <script>
    function validateForm() {
      var username = document.forms["updateForm"]["username"].value;
      var firstName = document.forms["updateForm"]["firstName"].value;
      var lastName = document.forms["updateForm"]["lastName"].value;
      var email = document.forms["updateForm"]["email"].value;
      var phoneNumber = document.forms["updateForm"]["phoneNumber"].value;

      if (
        username == "" ||
        firstName == "" ||
        lastName == "" ||
        email == "" ||
        phoneNumber == ""
      ) {
        alert("All fields must be filled out");
        return false;
      }
    }
  </script>
</html>

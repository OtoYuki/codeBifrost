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
      href="<%=request.getContextPath()%>/stylesheets/login.css"
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
        <h2>Login</h2>
        <form
          action="<%=request.getContextPath() + StringUtils.SERVLET_URL_LOGIN%>"
          method="post"
        >
          <div class="inputBx">
            <input type="text" name="username" placeholder="Username" />
          </div>
          <div class="inputBx">
            <input type="password" name="password" placeholder="Password" />
          </div>
          <div class="inputBx">
            <input type="submit" value="Sign in" />
          </div>
        </form>
        <div class="links">
          <a
            href="#"
            onclick="alert('Admin will get to you soon'); return false;"
            >Forget Password</a
          >
          <a
            href="<%=request.getContextPath() + StringUtils.PAGE_URL_REGISTER%>"
            >Signup</a
          >
        </div>
      </div>
    </div>
    <!--ring div ends here-->
  </body>
</html>

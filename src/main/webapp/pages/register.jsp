<%--
  Register Page JSP
  This form sends user data to RegisterServlet
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Register</title>
</head>
<body>

<%
    String errorMsg = (String) request.getAttribute("error");
%>
<%=
errorMsg
%>
<!-- Form starts here -->
<!-- action="/register" → matches your servlet URL -->
<!-- method="post" → used to send data securely -->
<form action="register" method="post">

    <!-- Full Name Field -->
    <div class="input-fields">
        <label>Full Name</label>
        <!-- name attribute is REQUIRED to get value in servlet -->
        <input type="text" name="name" placeholder="Enter your name" >
    </div>

    <!-- Email Field -->
    <div class="input-fields">
        <label>Email</label>
        <input type="email" name="email" placeholder="Enter your email" >
    </div>

    <!-- Password Field -->
    <div class="input-fields">
        <label>Password</label>
        <input type="password" name="password" placeholder="Enter password" >
    </div>

    <!-- Submit Button -->
    <div class="input-fields">
        <button type="submit">Register</button>
    </div>

</form>

</body>
</html>
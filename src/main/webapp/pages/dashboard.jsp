<%@ page import="com.learninglog.learninglogproject.user.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 3/29/2026
  Time: 11:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    User user =(User) session.getAttribute("user");
%>
<h1>
    Name: wellcome to dashboard
    <%= user.getName()  %>
    <br>
    Id: <%= user.getId() %>
</h1>

<a href="topic">
    <button type="button">Add Topic</button>
</a>
</body>

</html>
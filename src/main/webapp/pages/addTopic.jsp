<%@ page import="com.learninglog.learninglogproject.user.model.User" %>
<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 4/3/2026
  Time: 3:16 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
  <title>Add Topic</title>
  <style>
    body { font-family: Arial, sans-serif; margin: 24px; }
    .card { max-width: 520px; border: 1px solid #ddd; border-radius: 8px; padding: 16px; }
    .field { margin-bottom: 12px; }
    label { display: block; margin-bottom: 6px; font-weight: 600; }
    input[type="text"] { width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 6px; }
    .actions { display: flex; gap: 10px; margin-top: 10px; }
    button, .link-btn { padding: 8px 12px; border: 1px solid #bbb; background: #f6f6f6; border-radius: 6px; cursor: pointer; text-decoration: none; color: #111; }
    .error { color: #b00020; margin-bottom: 10px; }
  </style>
  </head>
<body>
<%
  String errorMsg = (String) request.getAttribute("error");
  if(errorMsg==null){
    errorMsg="";
  }
  User userObj= (User) session.getAttribute("user");
  int userId = 0;
  if(userObj != null){
    userId=userObj.getId();
  }
%>
<div class="card">
  <h2>Add Topic</h2>
  <% if (!errorMsg.isEmpty()) { %>
    <p class="error"><%= errorMsg %></p>
  <% } %>
  <form method="post" action="topic">
    <input type="hidden" value="add" name="action">
    <input type="hidden" value="<%=userId%>" name="user-id">
    <div class="field">
      <label for="topic-name">Topic Name</label>
      <input id="topic-name" type="text" value="" name="topic-name" placeholder="e.g., Java Collections" required>
    </div>
    <div class="actions">
      <button type="submit">Add Topic</button>
      <a class="link-btn" href="topic?page=list">View Topics</a>
    </div>
  </form>
</div>
</body>
</html>
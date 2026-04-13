<%--
  Created by IntelliJ IDEA.
  User: kusha
  Date: 4/13/2026
  Time: 3:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/components/header.jsp"/>

<html>
<head>
    <title>Edit Topic</title>
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
<div class="card">
    <h2>Edit Topic</h2>
    <p class="error">${error}</p>
    <form method="post" action="topic">
        <input type="hidden" value="edit" name="action">
        <input type="hidden" value="${topic.getId()}" name="id">

        <div class="field">
            <label for="topic-name">Topic Name</label>
            <input id="topic-name" type="text" value="${topic.getName()}" name="topic-name" required>
        </div>

        <div class="actions">
            <button type="submit">Update Topic</button>
            <a class="link-btn" href="topic?page=list">Back to List</a>
        </div>
    </form>
</div>
</body>
</html>
<jsp:include page="/components/footer.jsp"/>

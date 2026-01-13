<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Timetable Generator - ${param.title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<header>
        <h1>Timetable Generator System</h1>
        <div class="user-info">
            <span>Welcome, ${sessionScope.user.username} (${sessionScope.user.role})</span>
            <a href="${pageContext.request.contextPath}/user/logout">Logout</a>
        </div>
    </header>
</body>
</html>
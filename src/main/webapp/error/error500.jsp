<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Timetable Generator - Server Error</title>
</head>
<body>
<div class="error-container">
        <h1>500 - Internal Server Error</h1>
        <p>Something went wrong on our side. Please try again later.</p>
        <a href="${pageContext.request.contextPath}/" class="btn">Go to Homepage</a>
    </div>
<%@ include file="../common/footer.jsp" %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Timetable Generator - Login</title></head>
<body>
<div class="login-container">
        <h2>Login</h2>
        
        <c:if test="${not empty error}">
            <div class="alert error">${error}</div>
        </c:if>
        <c:if test="${not empty message}">
            <div class="alert success">${message}</div>
        </c:if>
       
        <form action="${pageContext.request.contextPath}/user" method="post">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>
            </div>
            
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <input type="hidden" value="login" />
            <button type="submit" class="btn">Login</button>
        </form>
        
        <p>Don't have an account? <a href="${pageContext.request.contextPath}/user">Register here</a></p>
    </div>
<%@ include file="../common/footer.jsp" %>
</body>
</html>
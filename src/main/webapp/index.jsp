<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Timetable Generator - Home</title>
</head>
<body>
 <div class="hero">
        <h1>Welcome to Timetable Generator System</h1>
        <p>Efficiently manage and generate timetables for your educational institution</p>
        
        <div class="cta-buttons">
            <a href="${pageContext.request.contextPath}/auth/login.jsp" class="btn">Login</a>
            <a href="${pageContext.request.contextPath}/auth/register.jsp" class="btn">Register</a>
        </div>
    </div>
    
    <style>
        .hero {
            text-align: center;
            padding: 100px 20px;
            background-color: #f8f9fa;
        }
        
        .hero h1 {
            font-size: 2.5em;
            margin-bottom: 20px;
            color: #2c3e50;
        }
        
        .hero p {
            font-size: 1.2em;
            color: #7f8c8d;
            margin-bottom: 30px;
        }
        
        .cta-buttons {
            display: flex;
            justify-content: center;
            gap: 15px;
        }
    </style>
<%@ include file="common/footer.jsp" %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Timetable Generator - Admin Dashboard</title>
</head>
<body>
<%@ include file="../common/sidebar.jsp" %>

<div class="main-content">
        <h1>Admin Dashboard</h1>
        
        <div class="dashboard-stats">
            <div class="stat-card">
                <h3>Departments</h3>
                <p>departmentCount</p>
            </div>
            
            <div class="stat-card">
                <h3>Teachers</h3>
                <p>teacherCount</p>
            </div>
            
            <div class="stat-card">
                <h3>Subjects</h3>
                <p>subjectCount</p>
            </div>
            
            <div class="stat-card">
                <h3>Batches</h3>
                <p>batchCount</p>
            </div>
        </div>
    </div>
<%@ include file="../common/footer.jsp" %>
</body>
</html>
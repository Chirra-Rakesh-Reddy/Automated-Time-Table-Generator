<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Timetable Generator - Classrooms</title>
</head>
<body>
 <%@ include file="../common/sidebar.jsp" %>
    
    <div class="main-content">
        <h1>Classrooms</h1>
        
        <c:if test="${not empty message}">
            <div class="alert success">${message}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alert error">${error}</div>
        </c:if>
        
        <div class="action-bar">
            <a href="${pageContext.request.contextPath}/admin/addClassroom" class="btn">Add Classroom</a>
        </div>
        
        <table class="data-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Room Number</th>
                    <th>Building</th>
                    <th>Capacity</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${classrooms}" var="room">
                    <tr>
                        <td>${room.roomId}</td>
                        <td>${room.roomNumber}</td>
                        <td>${room.building}</td>
                        <td>${room.capacity}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/editClassroom?id=${room.roomId}" class="btn edit">Edit</a>
                            <a href="${pageContext.request.contextPath}/admin/deleteClassroom?id=${room.roomId}" class="btn delete" onclick="return confirm('Are you sure?')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
<%@ include file="../common/footer.jsp" %>

</body>
</html>
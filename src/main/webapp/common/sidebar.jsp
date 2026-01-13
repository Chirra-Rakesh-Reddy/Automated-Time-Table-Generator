<%@ page language="java" pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="sidebar">
    <c:choose>
        <c:when test="${sessionScope.user.role == 'admin'}">
            <a href="${pageContext.request.contextPath}/adashboard.jsp">Dashboard</a>
            <a href="${pageContext.request.contextPath}/admin/departments">Departments</a>
            <a href="${pageContext.request.contextPath}/admin/teachers">Teachers</a>
            <a href="${pageContext.request.contextPath}/admin/subjects">Subjects</a>
            <a href="${pageContext.request.contextPath}/admin/batches">Batches</a>
            <a href="${pageContext.request.contextPath}/admin/classrooms">Classrooms</a>
            <a href="${pageContext.request.contextPath}/admin/slots">Time Slots</a>
            <a href="${pageContext.request.contextPath}/admin/users">Users</a>
            <a href="${pageContext.request.contextPath}/timetable/generate">Generate Timetable</a>
            <li class="nav-item">
    <a class="nav-link" href="${pageContext.request.contextPath}/timetables/list">
         View All Timetables
    </a>
</li>
        </c:when>
        <c:when test="${sessionScope.user.role == 'teacher'}">
            <a href="${pageContext.request.contextPath}/teacher/dashboard">Dashboard</a>
            <a href="${pageContext.request.contextPath}/teacher/timetable">My Timetable</a>
        </c:when>
        <c:when test="${sessionScope.user.role == 'student'}">
            <a href="${pageContext.request.contextPath}/student/dashboard">Dashboard</a>
            <a href="${pageContext.request.contextPath}/student/timetable">My Timetable</a>
        </c:when>
    </c:choose>
</div>
</body>
</html>
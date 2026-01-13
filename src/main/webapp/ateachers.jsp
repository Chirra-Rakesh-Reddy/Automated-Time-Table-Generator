<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Timetable Generator - Teachers</title>
</head>
<body>
 <%@ include file="../common/sidebar.jsp" %>
    
    <div class="main-content">
        <h1>Teachers</h1>
        
        <c:if test="${not empty message}">
            <div class="alert success">${message}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alert error">${error}</div>
        </c:if>
        
        <div class="action-bar">
            <a href="${pageContext.request.contextPath}/admin/addTeacher" class="btn">Add Teacher</a>
        </div>
        
        <table class="data-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Department</th>
                    <th>User Account</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${teachers}" var="teacher">
                    <tr>
                        <td>${teacher.teacherId}</td>
                        <td>${teacher.teacherName}</td>
                        <td>
                            <c:forEach items="${departments}" var="dept">
                                <c:if test="${dept.deptId == teacher.deptId}">${dept.deptName}</c:if>
                            </c:forEach>
                        </td>
                        <td>
                            <c:set var="user" value="${userDAO.getUserById(teacher.userId)}" />
                            ${user.username} (${user.role})
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/editTeacher?id=${teacher.teacherId}" class="btn edit">Edit</a>
                            <a href="${pageContext.request.contextPath}/admin/deleteTeacher?teacherId=${teacher.teacherId}" class="btn delete" onclick="return confirm('Are you sure?')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
<%@ include file="../common/footer.jsp" %>
</body>
</html>
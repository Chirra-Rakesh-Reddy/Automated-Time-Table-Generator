<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Timetable Generator - Subjects</title>
</head>
<body>
<%@ include file="../common/sidebar.jsp" %>
    
    <div class="main-content">
        <h1>Subjects</h1>
        
        <c:if test="${not empty message}">
            <div class="alert success">${message}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alert error">${error}</div>
        </c:if>
        
        <div class="action-bar">
            <a href="${pageContext.request.contextPath}/admin/addSubject" class="btn">Add Subject</a>
        </div>
        
        <table class="data-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Code</th>
                    <th>Department</th>
                    <th>Hours/Week</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${subjects}" var="subject">
                    <tr>
                        <td>${subject.subjectId}</td>
                        <td>${subject.subjectName}</td>
                        <td>${subject.subjectCode}</td>
                        <td>
                            <c:forEach items="${departments}" var="dept">
                                <c:if test="${dept.deptId == subject.deptId}">${dept.deptName}</c:if>
                            </c:forEach>
                        </td>
                        <td>${subject.hoursPerWeek}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/editSubject?id=${subject.subjectId}" class="btn edit">Edit</a>
                            <a href="${pageContext.request.contextPath}/admin/deleteSubject?id=${subject.subjectId}" class="btn delete" onclick="return confirm('Are you sure?')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
<%@ include file="../common/footer.jsp" %>
</body>
</html>
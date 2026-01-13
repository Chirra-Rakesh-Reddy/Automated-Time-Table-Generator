<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Timetable Generator - Departments</title>
</head>
<body>
 <%@ include file="../common/sidebar.jsp" %>
    
    <div class="main-content">
        <h1>Departments</h1>
        <form action="${pageContext.request.contextPath}/admin/listDepartments" method="get"></form>
        
        <c:if test="${not empty message}">
            <div class="alert success">${message}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alert error">${error}</div>
        </c:if>
        
        <div class="action-bar">
            <a href="${pageContext.request.contextPath}/admin/addDepartment" class="btn">Add Department</a>
        </div>
        
        <table class="data-table">
        
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Created At</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${departments}" var="department">
                    <tr>
                        <td>${department.deptId}</td>
                        <td>${department.deptName}</td>
                        <td><fmt:formatDate value="${dept.createdAt}" pattern="yyyy-MM-dd HH:mm"/></td>
                        <td>
                        	
                            <a href="${pageContext.request.contextPath}/admin/editDepartment?deptId=${department.deptId}" class="btn edit">Edit</a>
                            <a href="${pageContext.request.contextPath}/admin/deleteDepartment?deptId=${department.deptId}" class="btn delete" onclick="return confirm('Are you sure?')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
<%@ include file="../common/footer.jsp" %>

</body>
</html>
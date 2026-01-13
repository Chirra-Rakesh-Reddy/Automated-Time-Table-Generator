<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Timetable Generator - Batches</title>
</head>
<body>
<%@ include file="../common/sidebar.jsp" %>
    
    <div class="main-content">
        <h1>Batches</h1>
        
        <c:if test="${not empty message}">
            <div class="alert success">${message}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alert error">${error}</div>
        </c:if>
        
        <div class="action-bar">
            <a href="${pageContext.request.contextPath}/admin/addBatch" class="btn">Add Batch</a>
        </div>
        
        <table class="data-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Department</th>
                    <th>Academic Year</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${batches}" var="batch">
                    <tr>
                        <td>${batch.batchId}</td>
                        <td>${batch.batchName}</td>
                        <td>
                            <c:forEach items="${departments}" var="dept">
                                <c:if test="${dept.deptId == batch.deptId}">${dept.deptName}</c:if>
                            </c:forEach>
                        </td>
                        <td>${batch.academicYear}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/editBatch?id=${batch.batchId}" class="btn edit">Edit</a>
                            <a href="${pageContext.request.contextPath}/admin/deleteBatch?id=${batch.batchId}" class="btn delete" onclick="return confirm('Are you sure?')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
<%@ include file="../common/footer.jsp" %>

</body>
</html>
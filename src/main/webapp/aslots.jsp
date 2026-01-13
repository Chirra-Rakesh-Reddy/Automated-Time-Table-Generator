<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Timetable Generator - Time Slots</title>
</head>
<body>
<%@ include file="../common/sidebar.jsp" %>
    
    <div class="main-content">
        <h1>Time Slots</h1>
        
        <c:if test="${not empty message}">
            <div class="alert success">${message}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alert error">${error}</div>
        </c:if>
        
        <div class="action-bar">
            <a href="${pageContext.request.contextPath}/admin/addSlot" class="btn">Add Time Slot</a>
        </div>
        
        <table class="data-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Day</th>
                    <th>Start Time</th>
                    <th>End Time</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${slots}" var="slot">
                    <tr>
                        <td>${slot.slotId}</td>
                        <td>${slot.dayOfWeek}</td>
                        <td><fmt:formatDate value="${slot.startTime}" pattern="HH:mm"/></td>
                        <td><fmt:formatDate value="${slot.endTime}" pattern="HH:mm"/></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/editSlot?id=${slot.slotId}" class="btn edit">Edit</a>
                            <a href="${pageContext.request.contextPath}/admin/deleteSlot?id=${slot.slotId}" class="btn delete" onclick="return confirm('Are you sure?')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
<%@ include file="../common/footer.jsp" %>
</body>
</html>
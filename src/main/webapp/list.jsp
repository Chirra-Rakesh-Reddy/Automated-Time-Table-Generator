<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   <%@ include file="../common/header.jsp" %>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Timetable Generator - List</title>
</head>
<body>
<div class="container">
    <h2>Generated Timetables</h2>
    
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Batch</th>
                <th>Department</th>
                <th>Scheduled Sessions</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${timetables}" var="timetable">
            <tr>
                <td>${timetable.batchName}</td>
                <td>${timetable.department}</td>
                <td>${timetable.entryCount}</td>
                <td>
                   <a href="${pageContext.request.contextPath}/timetables/view?batchId=${timetable.batchId}">View</a>
                </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="../common/footer.jsp" %>
</body>
</html>
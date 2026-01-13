<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Timetable Generator - Add Department</title>
</head>
<body>
<%@ include file="../common/sidebar.jsp" %>
    
    <div class="main-content">
        <h1>Add Department</h1>
        
        <c:if test="${not empty error}">
            <div class="alert error">${error}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/admin/addDepartment" method="post">
            <div class="form-group">
                <label for="deptName">Department Name:</label>
                <input type="text" id="deptName" name="deptName" required>
            </div>
            
            <button type="submit" class="btn">Add Department</button>
            <a href="${pageContext.request.contextPath}/adepartments.jsp" class="btn cancel">Cancel</a>
        </form>
    </div>
<%@ include file="../common/footer.jsp" %>
</body>
</html>
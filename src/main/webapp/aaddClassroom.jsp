<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Timetable Generator - Add Classroom</title>
</head>
<body>
<%@ include file="../common/sidebar.jsp" %>
    
    <div class="main-content">
        <h1>Add Classroom</h1>
        
        <c:if test="${not empty error}">
            <div class="alert error">${error}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/admin/addClassroom" method="post">
            <div class="form-group">
                <label for="roomNumber">Room Number:</label>
                <input type="text" id="roomNumber" name="roomNumber" required>
            </div>
            
            <div class="form-group">
                <label for="building">Building:</label>
                <input type="text" id="building" name="building" required>
            </div>
            
            <div class="form-group">
                <label for="capacity">Capacity:</label>
                <input type="number" id="capacity" name="capacity" min="1" required>
            </div>
            
            <button type="submit" class="btn">Add Classroom</button>
            <a href="${pageContext.request.contextPath}/aclassrooms.jsp" class="btn cancel">Cancel</a>
        </form>
    </div>
<%@ include file="../common/footer.jsp" %>
</body>
</html>
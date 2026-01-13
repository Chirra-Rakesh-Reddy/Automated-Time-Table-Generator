<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Timetable Generator - Add Time Slot</title>
</head>
<body>
<%@ include file="../common/sidebar.jsp" %>
    
    <div class="main-content">
        <h1>Add Time Slot</h1>
        
        <c:if test="${not empty error}">
            <div class="alert error">${error}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/admin/addSlot" method="post">
            <div class="form-group">
                <label for="dayOfWeek">Day of Week:</label>
                <select id="dayOfWeek" name="dayOfWeek" required>
                    <option value="">Select Day</option>
                    <option value="Monday">Monday</option>
                    <option value="Tuesday">Tuesday</option>
                    <option value="Wednesday">Wednesday</option>
                    <option value="Thursday">Thursday</option>
                    <option value="Friday">Friday</option>
                    <option value="Saturday">Saturday</option>
                </select>
            </div>
            
            <div class="form-group">
                <label for="startTime">Start Time:</label>
                <input type="time" id="startTime" name="startTime" required>
            </div>
            
            <div class="form-group">
                <label for="endTime">End Time:</label>
                <input type="time" id="endTime" name="endTime" required>
            </div>
            
            <button type="submit" class="btn">Add Time Slot</button>
            <a href="${pageContext.request.contextPath}/aslots.jsp" class="btn cancel">Cancel</a>
        </form>
    </div>
<%@ include file="../common/footer.jsp" %>
</body>
</html>
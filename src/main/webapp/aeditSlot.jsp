<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Timetable Generator - Edit Time Slot</title>
</head>
<body>
 <%@ include file="../common/sidebar.jsp" %>
    
    <div class="main-content">
        <h1>Edit Time Slot</h1>
        
        <c:if test="${not empty error}">
            <div class="alert error">${error}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/admin/updateSlot" method="post">
            <input type="hidden" name="slotId" value="${slot.slotId}">
            
            <div class="form-group">
                <label for="dayOfWeek">Day of Week:</label>
                <select id="dayOfWeek" name="dayOfWeek" required>
                    <option value="">Select Day</option>
                    <option value="Monday" ${slot.dayOfWeek == 'Monday' ? 'selected' : ''}>Monday</option>
                    <option value="Tuesday" ${slot.dayOfWeek == 'Tuesday' ? 'selected' : ''}>Tuesday</option>
                    <option value="Wednesday" ${slot.dayOfWeek == 'Wednesday' ? 'selected' : ''}>Wednesday</option>
                    <option value="Thursday" ${slot.dayOfWeek == 'Thursday' ? 'selected' : ''}>Thursday</option>
                    <option value="Friday" ${slot.dayOfWeek == 'Friday' ? 'selected' : ''}>Friday</option>
                    <option value="Saturday" ${slot.dayOfWeek == 'Saturday' ? 'selected' : ''}>Saturday</option>
                </select>
            </div>
            
            <div class="form-group">
                <label for="startTime">Start Time:</label>
                <input type="time" id="startTime" name="startTime" value="<fmt:formatDate value="${slot.startTime}" pattern="HH:mm"/>" required>
            </div>
            
            <div class="form-group">
                <label for="endTime">End Time:</label>
                <input type="time" id="endTime" name="endTime" value="<fmt:formatDate value="${slot.endTime}" pattern="HH:mm"/>" required>
            </div>
            
            <button type="submit" class="btn">Update Time Slot</button>
            <a href="${pageContext.request.contextPath}/aslots.jsp" class="btn cancel">Cancel</a>
        </form>
    </div>
<%@ include file="../common/footer.jsp" %>
</body>
</html>
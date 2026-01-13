<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Timetable Generator - Add Subject</title>
</head>
<body>
 <%@ include file="../common/sidebar.jsp" %>
    
    <div class="main-content">
        <h1>Add Subject</h1>
        
        <c:if test="${not empty error}">
            <div class="alert error">${error}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/admin/addSubject" method="post">
            <div class="form-group">
                <label for="subjectName">Subject Name:</label>
                <input type="text" id="subjectName" name="subjectName" required>
            </div>
            
            <div class="form-group">
                <label for="subjectCode">Subject Code:</label>
                <input type="text" id="subjectCode" name="subjectCode" required>
            </div>
            
            <div class="form-group">
                <label for="deptId">Department:</label>
                <select id="deptId" name="deptId" required>
                    <option value="">Select Department</option>
                    <c:forEach items="${departments}" var="dept">
                        <option value="${dept.deptId}">${dept.deptName}</option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="form-group">
                <label for="hoursPerWeek">Hours Per Week:</label>
                <input type="number" id="hoursPerWeek" name="hoursPerWeek" min="1" max="10" value="3" required>
            </div>
            
            <button type="submit" class="btn">Add Subject</button>
            <a href="${pageContext.request.contextPath}/asubjects.jsp" class="btn cancel">Cancel</a>
        </form>
    </div>
<%@ include file="../common/footer.jsp" %>
</body>
</html>
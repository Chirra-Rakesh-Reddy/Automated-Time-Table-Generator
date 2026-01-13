<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Timetable Generator - Edit Teacher</title>
</head>
<body>
<%@ include file="../common/sidebar.jsp" %>
    
    <div class="main-content">
        <h1>Edit Teacher</h1>
        
        <c:if test="${not empty error}">
            <div class="alert error">${error}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/admin/updateTeacher" method="post">
            <input type="hidden" name="teacherId" value="${teacher.teacherId}">
            
            <div class="form-group">
                <label for="teacherName">Teacher Name:</label>
                <input type="text" id="teacherName" name="teacherName" value="${teacher.teacherName}" required>
            </div>
            
            <div class="form-group">
                <label for="deptId">Department:</label>
                <select id="deptId" name="deptId" required>
                    <option value="">Select Department</option>
                    <c:forEach items="${departments}" var="dept">
                        <option value="${dept.deptId}" ${dept.deptId == teacher.deptId ? 'selected' : ''}>${dept.deptName}</option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="form-group">
                <label for="userId">User Account:</label>
                <select id="userId" name="userId" required>
                    <option value="">Select User</option>
                    <c:forEach items="${users}" var="user">
                        <option value="${user.userId}" ${user.userId == teacher.userId ? 'selected' : ''}>${user.username} (${user.role})</option>
                    </c:forEach>
                </select>
            </div>
            
            <button type="submit" class="btn">Update Teacher</button>
            <a href="${pageContext.request.contextPath}/ateachers.jsp" class="btn cancel">Cancel</a>
        </form>
    </div>
<%@ include file="../common/footer.jsp" %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Timetable Generator - Edit User</title>
</head>
<body>
 <%@ include file="../common/sidebar.jsp" %>
    
    <div class="main-content">
        <h1>Edit User</h1>
        
        <c:if test="${not empty error}">
            <div class="alert error">${error}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/admin/updateUser" method="post">
            <input type="hidden" name="userId" value="${user.userId}">
            
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" value="${user.username}" required>
            </div>
            
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${user.email}" required>
            </div>
            
            <div class="form-group">
                <label for="role">Role:</label>
                <select id="role" name="role" required>
                    <option value="">Select Role</option>
                    <option value="admin" ${user.role == 'admin' ? 'selected' : ''}>Admin</option>
                    <option value="teacher" ${user.role == 'teacher' ? 'selected' : ''}>Teacher</option>
                </select>
            </div>
            
            <button type="submit" class="btn">Update User</button>
            <a href="${pageContext.request.contextPath}/user/users.jsp" class="btn cancel">Cancel</a>
        </form>
    </div>
<%@ include file="../common/footer.jsp" %>
</body>
</html>
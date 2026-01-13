<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../common/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Timetable Generator - Edit Batch</title>
</head>
<body>
<%@ include file="../common/sidebar.jsp" %>
    
    <div class="main-content">
        <h1>Edit Batch</h1>
        
        <c:if test="${not empty error}">
            <div class="alert error">${error}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/admin/updateBatch" method="post">
            <input type="hidden" name="batchId" value="${batch.batchId}">
            
            <div class="form-group">
                <label for="batchName">Batch Name:</label>
                <input type="text" id="batchName" name="batchName" value="${batch.batchName}" required>
            </div>
            
            <div class="form-group">
                <label for="deptId">Department:</label>
                <select id="deptId" name="deptId" required>
                    <option value="">Select Department</option>
                    <c:forEach items="${departments}" var="dept">
                        <option value="${dept.deptId}" ${dept.deptId == batch.deptId ? 'selected' : ''}>${dept.deptName}</option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="form-group">
                <label for="academicYear">Academic Year:</label>
                <input type="text" id="academicYear" name="academicYear" value="${batch.academicYear}" placeholder="YYYY-YYYY" required>
            </div>
            
            <button type="submit" class="btn">Update Batch</button>
            <a href="${pageContext.request.contextPath}/abatches.jsp" class="btn cancel">Cancel</a>
        </form>
    </div>
<%@ include file="../common/footer.jsp" %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Timetable Generator - Admin Dashboard</title>
</head>
<body>
 <%@ include file="../common/sidebar.jsp" %>
    
    <div class="main-content">
        <h1>Admin Dashboard</h1>
        
        <div class="dashboard-stats">
            <div class="stat-card">
                <h3>Departments</h3>
                <p>${departmentDAO.getAllDepartments().size()}</p>
                <a href="${pageContext.request.contextPath}/admin/departments" class="btn">Manage</a>
            </div>
            
            <div class="stat-card">
                <h3>Teachers</h3>
                <p>${teacherDAO.getAllTeachers().size()}</p>
                <a href="${pageContext.request.contextPath}/admin/teachers" class="btn">Manage</a>
            </div>
            
            <div class="stat-card">
                <h3>Subjects</h3>
                <p>${subjectDAO.getAllSubjects().size()}</p>
                <a href="${pageContext.request.contextPath}/admin/subjects" class="btn">Manage</a>
            </div>
            
            <div class="stat-card">
                <h3>Batches</h3>
                <p>${batchDAO.getAllBatches().size()}</p>
                <a href="${pageContext.request.contextPath}/admin/batches" class="btn">Manage</a>
            </div>
            
            <div class="stat-card">
                <h3>Classrooms</h3>
                <p>${classroomDAO.getAllClassrooms().size()}</p>
                <a href="${pageContext.request.contextPath}/admin/classrooms" class="btn">Manage</a>
            </div>
            
            <div class="stat-card">
                <h3>Time Slots</h3>
                <p>${slotDAO.getAllSlots().size()}</p>
                <a href="${pageContext.request.contextPath}/admin/slots" class="btn">Manage</a>
            </div>
            
            <div class="stat-card">
                <h3>Users</h3>
                <p>${userDAO.getAllUsers().size()}</p>
                <a href="${pageContext.request.contextPath}/admin/users" class="btn">Manage</a>
            </div>
        </div>
    </div>
    
    <style>
        .dashboard-stats {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 20px;
            margin-top: 30px;
        }
        
        .stat-card {
            background: white;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            text-align: center;
        }
        
        .stat-card h3 {
            margin-top: 0;
            color: #7f8c8d;
            font-size: 1.2em;
        }
        
        .stat-card p {
            font-size: 2em;
            font-weight: bold;
            margin: 15px 0;
            color: #2c3e50;
        }
        
        .stat-card .btn {
            display: block;
            width: 100%;
            padding: 8px;
            font-size: 0.9em;
        }
    </style>
<%@ include file="../common/footer.jsp" %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   <%@ include file="../common/header.jsp" %>
         <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
         <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
         
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Timetable Generator - Details</title>
</head>
<style>
        table { width: 100%; border-collapse: collapse; }
        th, td { padding: 8px; text-align: left; border-bottom: 1px solid #ddd; }
        th { background-color: #f2f2f2; }
    </style>
<body>
<div class="container">
   <h1>Timetable for ${entries[0].batch.batchName}</h1>
    
    <c:if test="${not empty warning}">
        <div class="warning">${warning}</div>
    </c:if>
    
   <table class="timetable-grid">
        <thead>
            <tr>
                <th>Time/Day</th>
                <th>Monday</th>
                <th>Tuesday</th>
                <th>Wednesday</th>
                <th>Thursday</th>
                <th>Friday</th>
                <th>Saturday</th>
            </tr>
        </thead>
        <tbody>
            <!-- Time slots should be dynamically generated from your data -->
            <c:set var="timeSlotsString" value="09:00:00,10:00:00,11:00:00,01:00:00,02:00:00" />
<c:set var="timeSlots" value="${fn:split(timeSlotsString, ',')}" />
            <c:forEach items="${timeSlots}" var="time">
    <tr>
        <td class="time-col">
            <c:choose>
                <c:when test="${time == '09:00:00'}">09:00 - 10:00</c:when>
                <c:when test="${time == '10:00:00'}">10:00 - 11:00</c:when>
                <c:when test="${time == '11:00:00'}">11:00 - 12:00</c:when>
                <c:when test="${time == '01:00:00'}">01:00 - 02:00</c:when>
                <c:when test="${time == '02:00:00'}">02:00 - 03:00</c:when>
                <c:otherwise>${time}</c:otherwise>
            </c:choose>
        </td>
        <c:forEach items="${daysOfWeek}" var="day">
            <td class="subject-cell">
                <c:forEach items="${entries}" var="entry">
                    <c:if test="${entry.slot.dayOfWeek == day && entry.slot.startTime == time}">
                        <div class="subject">${entry.subject.subjectName}</div>
                        <div class="code">${entry.subject.subjectCode}</div>
                        <div class="teacher">${entry.teacher.teacherName}</div>
                        <div class="room">${entry.classroom.building} ${entry.classroom.roomNumber}</div>
                    </c:if>
                </c:forEach>
            </td>
        </c:forEach>
    </tr>
</c:forEach>
        </tbody>
    </table>

<div class="mb-3 no-print">
    <a href="#" onclick="window.print()" class="btn btn-info">
        <i class="fas fa-print"></i> Print
    </a>
    
</div>


<%@ include file="../common/footer.jsp" %>
</body>
</html>
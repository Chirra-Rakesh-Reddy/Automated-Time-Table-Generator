package com.timetable.controller;

import com.timetable.dao.*;
import com.timetable.model.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/student/*")
public class StudentController extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TimetableDAO timetableDAO;
    private StudentDAO studentDAO;
    
    @Override
    public void init() {
        timetableDAO = new TimetableDAO();
        studentDAO = new StudentDAO();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        
        User user = (User) session.getAttribute("user");
        if (!"student".equals(user.getRole())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        
        String action = request.getPathInfo() != null ? request.getPathInfo() : "/dashboard";
        
        try {
            switch (action) {
                case "/dashboard":
                    showDashboard(request, response);
                    break;
                case "/timetable":
                    viewTimetable(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    
    private void showDashboard(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        User user = (User) request.getSession().getAttribute("user");
        Student student = studentDAO.getStudentByUserId(user.getUserId());
        
        request.setAttribute("student", student);
        request.getRequestDispatcher("/sdashboard.jsp").forward(request, response);
    }
    
    private void viewTimetable(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        User user = (User) request.getSession().getAttribute("user");
        Student student = studentDAO.getStudentByUserId(user.getUserId());
        List<TimetableEntry> entries = timetableDAO.getTimetableByBatch(student.getBatchId());
        
        request.setAttribute("entries", entries);
        request.getRequestDispatcher("/stimetable.jsp").forward(request, response);
    }
}
package com.timetable.controller;

import com.timetable.dao.*;
import com.timetable.model.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/teacher/*")
public class TeacherController extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TimetableDAO timetableDAO;
    private TeacherDAO teacherDAO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        timetableDAO = new TimetableDAO();
        teacherDAO = new TeacherDAO();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getPathInfo();
        
        if (action == null) {
            action = "/dashboard";
        }
        
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
            throws ServletException, IOException {
        request.getRequestDispatcher("/tdashboard.jsp").forward(request, response);
    }
    
    private void viewTimetable(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        Teacher teacher = teacherDAO.getTeacherByUserId(user.getUserId());
        if (teacher == null) {
            request.setAttribute("error", "Teacher not found.");
            showDashboard(request, response);
            return;
        }
        
        List<TimetableEntry> entries = timetableDAO.getTimetableByTeacher(teacher.getTeacherId());
        request.setAttribute("entries", entries);
        request.getRequestDispatcher("/ttimetable.jsp").forward(request, response);
    }
}
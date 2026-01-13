package com.timetable.controller;

import com.timetable.dao.*;
import com.timetable.model.*;
import com.timetable.util.TimetableGenerator;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/timetable/*")
public class TimetableController extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TimetableDAO timetableDAO;
	private TimetableSlotDAO timetableSlotDAO;
    private DepartmentDAO departmentDAO;
    private BatchDAO batchDAO;
    private TeacherDAO teacherDAO;  // Added this line
    private ClassroomDAO classroomDAO;  // Added this line
    private TimetableGenerator timetableGenerator;
    
    @Override
    public void init() throws ServletException {
        super.init();
        timetableDAO = new TimetableDAO();
        timetableSlotDAO = new TimetableSlotDAO();
        departmentDAO = new DepartmentDAO();
        batchDAO = new BatchDAO();
        teacherDAO = new TeacherDAO();  // Added this line
        classroomDAO = new ClassroomDAO();  // Added this line
        timetableGenerator = new TimetableGenerator();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getPathInfo();
        
        if (action == null) {
            action = "/view";
        }
        
        try {
            switch (action) {
                case "/view":
                    viewTimetable(request, response);
                    break;
                case "/generate":
                    showGenerateForm(request, response);
                    break;
                case "/batch":
                    viewBatchTimetable(request, response);
                    break;
                case "/teacher":
                    viewTeacherTimetable(request, response);
                    break;
                case "/classroom":
                    viewClassroomTimetable(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getPathInfo();
        
        if (action == null) {
            action = "/view";
        }
        
        try {
            switch (action) {
                case "/generate":
                    generateTimetable(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    
    private void viewTimetable(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<TimetableEntry> entries = timetableDAO.getAllTimetableEntries();
        request.setAttribute("entries", entries);
        List<TimetableSlot> slots = timetableSlotDAO.getAllSlots();
        request.setAttribute("timeSlots", slots);
        List<String> days = Arrays.asList("Monday","Tuesday","Wednesday","Thursday","Friday","Saturay");
        request.setAttribute("days", days);
        System.out.println("Entries new pt\t"+entries);
        request.getRequestDispatcher("/tviewTimetable.jsp").forward(request, response);
    }
    
    private void showGenerateForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Department> departments = departmentDAO.getAllDepartments();
        request.setAttribute("departments", departments);
        System.out.println(departments.toString());
        request.getRequestDispatcher("/tgenerateTimetable.jsp").forward(request, response);
    }
    
    private void generateTimetable(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int batchId = Integer.parseInt(request.getParameter("batchId"));
        
        boolean success = timetableGenerator.generateTimetable(batchId);
        
        if (success) {
            request.setAttribute("message", "Timetable generated successfully!");
        } else {
            request.setAttribute("error", "Failed to generate timetable. Please check if all required data is available.");
        }
        
        //showGenerateForm(request, response);
        viewTimetable(request, response);
    }
    
    private void viewBatchTimetable(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int batchId = Integer.parseInt(request.getParameter("id"));
        Batch batch = batchDAO.getBatchById(batchId);
        List<TimetableEntry> entries = timetableDAO.getTimetableByBatch(batchId);
        
        request.setAttribute("batch", batch);
        request.setAttribute("entries", entries);
        request.getRequestDispatcher("/batchTimetable.jsp").forward(request, response);
    }
    
    private void viewTeacherTimetable(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int teacherId = Integer.parseInt(request.getParameter("id"));
        Teacher teacher = teacherDAO.getTeacherById(teacherId);
        List<TimetableEntry> entries = timetableDAO.getTimetableByTeacher(teacherId);
        
        request.setAttribute("teacher", teacher);
        request.setAttribute("entries", entries);
        request.getRequestDispatcher("/teacherTimetable.jsp").forward(request, response);
    }
    
    private void viewClassroomTimetable(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int roomId = Integer.parseInt(request.getParameter("id"));
        Classroom classroom = classroomDAO.getClassroomById(roomId);
        List<TimetableEntry> entries = timetableDAO.getTimetableByClassroom(roomId);
        
        request.setAttribute("classroom", classroom);
        request.setAttribute("entries", entries);
        request.getRequestDispatcher("/classroomTimetable.jsp").forward(request, response);
    }
}
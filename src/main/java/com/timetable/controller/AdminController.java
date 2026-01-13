package com.timetable.controller;

import com.timetable.dao.*;
import com.timetable.model.*;
import java.io.IOException;
import java.sql.Time;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/*")
public class AdminController extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DepartmentDAO departmentDAO;
    private TeacherDAO teacherDAO;
    private SubjectDAO subjectDAO;
    private BatchDAO batchDAO;
    private ClassroomDAO classroomDAO;
    private TimetableSlotDAO slotDAO;
    private UserDAO userDAO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        departmentDAO = new DepartmentDAO();
        teacherDAO = new TeacherDAO();
        subjectDAO = new SubjectDAO();
        batchDAO = new BatchDAO();
        classroomDAO = new ClassroomDAO();
        slotDAO = new TimetableSlotDAO();
        userDAO = new UserDAO();
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
                case "/departments":
                    listDepartments(request, response);
                    break;
                case "/addDepartment":
                    showAddDepartmentForm(request, response);
                    break;
                case "/editDepartment":
                	System.out.println("In edit dept");
                    showEditDepartmentForm(request, response);
                    break;
                case "/deleteDepartment":
                    deleteDepartment(request, response);
                    break;
                case "/teachers":
                    listTeachers(request, response);
                    break;
                case "/addTeacher":
                    showAddTeacherForm(request, response);
                    break;
                case "/editTeacher":
                    showEditTeacherForm(request, response);
                    break;
                case "/deleteTeacher":
                    deleteTeacher(request, response);
                    break;
                case "/subjects":
                    listSubjects(request, response);
                    break;
                case "/addSubject":
                    showAddSubjectForm(request, response);
                    break;
                case "/editSubject":
                    showEditSubjectForm(request, response);
                    break;
                case "/deleteSubject":
                    deleteSubject(request, response);
                    break;
                case "/batches":
                    listBatches(request, response);
                    break;
                case "/addBatch":
                    showAddBatchForm(request, response);
                    break;
                case "/editBatch":
                    showEditBatchForm(request, response);
                    break;
                case "/classrooms":
                    listClassrooms(request, response);
                    break;
                case "/addClassroom":
                    showAddClassroomForm(request, response);
                    break;
                case "/editClassroom":
                    showEditClassroomForm(request, response);
                    break;
                case "/deleteClassroom":
                    deleteClassroom(request, response);
                    break;
                case "/slots":
                    listSlots(request, response);
                    break;
                case "/addSlot":
                    showAddSlotForm(request, response);
                    break;
                    
                case "/editSlot":
                    showEditSlotForm(request, response);
                    break;
                case "/deleteSlot":
                    deleteSlot(request, response);
                    break;
                case "/users":
                    listUsers(request, response);
                    break;
               case "/addUser":
                    showAddUserForm(request, response);
                    break;
                case "/editUser":
                    showEditUserForm(request, response);
                    break;
                case "/deleteUser":
                    deleteUser(request, response);
                    break;
                case "/deleteBatch":
                    deleteBatch(request, response);
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
            action = "/dashboard";
        }
        
        try {
            switch (action) {
                case "/addDepartment":
                    addDepartment(request, response);
                    break;
                case "/updateDepartment":
                    updateDepartment(request, response);
                    break;
                case "/deleteDepartment":
                    deleteDepartment(request, response);
                    break;
                case "/addTeacher":
                    addTeacher(request, response);
                    break;
                case "/updateTeacher":
                    updateTeacher(request, response);
                    break;
                case "/deleteTeacher":
                    deleteTeacher(request, response);
                    break;
                case "/addSubject":
                    addSubject(request, response);
                    break;
                case "/updateSubject":
                    updateSubject(request, response);
                    break;
                case "/deleteSubject":
                    deleteSubject(request, response);
                    break;
                case "/addBatch":
                    addBatch(request, response);
                    break;
                case "/updateBatch":
                    updateBatch(request, response);
                    break;
                case "/deleteBatch":
                    deleteBatch(request, response);
                    break;
                case "/addClassroom":
                    addClassroom(request, response);
                    break;
                case "/updateClassroom":
                    updateClassroom(request, response);
                    break;
                case "/deleteClassroom":
                    deleteClassroom(request, response);
                    break;
                case "/addSlot":
                    addSlot(request, response);
                    break;
                case "/updateSlot":
                    updateSlot(request, response);
                    break;
                case "/deleteSlot":
                    deleteSlot(request, response);
                    break;
                case "/addUser":
                    addUser(request, response);
                    break;
                case "/updateUser":
                    updateUser(request, response);
                    break;
                case "/deleteUser":
                    deleteUser(request, response);
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
        request.getRequestDispatcher("/adashboard.jsp").forward(request, response);
    }
    
    private void listDepartments(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Department> departments = departmentDAO.getAllDepartments();
      //  System.out.println(departments.toString());
        request.setAttribute("departments", departments);
        request.getRequestDispatcher("/adepartments.jsp").forward(request, response);
    }
    
    private void showAddDepartmentForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/aaddDepartment.jsp").forward(request, response);
    }
    
    private void showEditDepartmentForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, NumberFormatException {
        int deptId = Integer.parseInt(request.getParameter("deptId"));
        System.out.println(deptId);
        Department department = departmentDAO.getDepartmentById(deptId);
        request.setAttribute("department", department);
        System.out.println(department.toString());
        request.getRequestDispatcher("/aeditDepartment.jsp").forward(request, response);
    }
    
    private void addDepartment(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String deptName = request.getParameter("deptName");
        
        Department department = new Department();
        department.setDeptName(deptName);
        
        if (departmentDAO.addDepartment(department)) {
            request.setAttribute("message", "Department added successfully!");
        } else {
            request.setAttribute("error", "Failed to add department.");
        }
        
        listDepartments(request, response);
    }
    
    private void updateDepartment(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, NumberFormatException {
    	 	System.out.println(request.getParameter("deptId"));
        int deptId = Integer.parseInt(request.getParameter("deptId"));
        String deptName = request.getParameter("deptName");
        
        Department department = new Department();
        department.setDeptId(deptId);
        department.setDeptName(deptName);
        
        if (departmentDAO.updateDepartment(department)) {
            request.setAttribute("message", "Department updated successfully!");
        } else {
            request.setAttribute("error", "Failed to update department.");
        }
        
        listDepartments(request, response);
    }
    
    private void deleteDepartment(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int deptId = Integer.parseInt(request.getParameter("deptId"));
        System.out.println("In delete depaertment\t"+deptId);
        if (departmentDAO.deleteDepartment(deptId)) {
            request.setAttribute("message", "Department deleted successfully!");
        } else {
            request.setAttribute("error", "Failed to delete department.");
        }
        
        listDepartments(request, response);
    }
    
    
    private void listTeachers(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Teacher> teachers = teacherDAO.getAllTeachers();
        List<Department> departments = departmentDAO.getAllDepartments();
        request.setAttribute("teachers", teachers);
        request.setAttribute("departments", departments);
        request.getRequestDispatcher("/ateachers.jsp").forward(request, response);
    }
    
    private void showAddTeacherForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Department> departments = departmentDAO.getAllDepartments();
        List<User> users = userDAO.getAllUsers();
        request.setAttribute("departments", departments);
        request.setAttribute("users", users);
        request.getRequestDispatcher("/aaddTeacher.jsp").forward(request, response);
    }
    
    private void addTeacher(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String teacherName = request.getParameter("teacherName");
        int deptId = Integer.parseInt(request.getParameter("deptId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        
        Teacher teacher = new Teacher();
        teacher.setTeacherName(teacherName);
        teacher.setDeptId(deptId);
        teacher.setUserId(userId);
        
        if (teacherDAO.addTeacher(teacher)) {
            request.setAttribute("message", "Teacher added successfully!");
        } else {
            request.setAttribute("error", "Failed to add teacher.");
        }
        
        listTeachers(request, response);
    }
    
 // Subject related methods
    private void listSubjects(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Subject> subjects = subjectDAO.getAllSubjects();
        List<Department> departments = departmentDAO.getAllDepartments();
        request.setAttribute("subjects", subjects);
        request.setAttribute("departments", departments);
        request.getRequestDispatcher("/asubjects.jsp").forward(request, response);
    }

    private void showAddSubjectForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Department> departments = departmentDAO.getAllDepartments();
        request.setAttribute("departments", departments);
        request.getRequestDispatcher("/aaddSubject.jsp").forward(request, response);
    }

    private void showEditSubjectForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int subjectId = Integer.parseInt(request.getParameter("id"));
        Subject subject = subjectDAO.getSubjectById(subjectId);
        List<Department> departments = departmentDAO.getAllDepartments();
        
        request.setAttribute("subject", subject);
        request.setAttribute("departments", departments);
        request.getRequestDispatcher("/aeditSubject.jsp").forward(request, response);
    }

    private void addSubject(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String subjectName = request.getParameter("subjectName");
        String subjectCode = request.getParameter("subjectCode");
        int deptId = Integer.parseInt(request.getParameter("deptId"));
        int hoursPerWeek = Integer.parseInt(request.getParameter("hoursPerWeek"));
        
        Subject subject = new Subject();
        subject.setSubjectName(subjectName);
        subject.setSubjectCode(subjectCode);
        subject.setDeptId(deptId);
        subject.setHoursPerWeek(hoursPerWeek);
        
        if (subjectDAO.addSubject(subject)) {
            request.setAttribute("message", "Subject added successfully!");
        } else {
            request.setAttribute("error", "Failed to add subject.");
        }
        
        listSubjects(request, response);
    }

    private void updateSubject(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        String subjectName = request.getParameter("subjectName");
        String subjectCode = request.getParameter("subjectCode");
        int deptId = Integer.parseInt(request.getParameter("deptId"));
        int hoursPerWeek = Integer.parseInt(request.getParameter("hoursPerWeek"));
        
        Subject subject = new Subject();
        subject.setSubjectId(subjectId);
        subject.setSubjectName(subjectName);
        subject.setSubjectCode(subjectCode);
        subject.setDeptId(deptId);
        subject.setHoursPerWeek(hoursPerWeek);
        
        if (subjectDAO.updateSubject(subject)) {
            request.setAttribute("message", "Subject updated successfully!");
        } else {
            request.setAttribute("error", "Failed to update subject.");
        }
        
        listSubjects(request, response);
    }

    private void deleteSubject(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int subjectId = Integer.parseInt(request.getParameter("id"));
        
        if (subjectDAO.deleteSubject(subjectId)) {
            request.setAttribute("message", "Subject deleted successfully!");
        } else {
            request.setAttribute("error", "Failed to delete subject.");
        }
        
        listSubjects(request, response);
    }

    // Batch related methods
    private void listBatches(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Batch> batches = batchDAO.getAllBatches();
        List<Department> departments = departmentDAO.getAllDepartments();
        request.setAttribute("batches", batches);
        request.setAttribute("departments", departments);
        request.getRequestDispatcher("/abatches.jsp").forward(request, response);
    }

    private void showAddBatchForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Department> departments = departmentDAO.getAllDepartments();
        request.setAttribute("departments", departments);
        request.getRequestDispatcher("/aaddBatch.jsp").forward(request, response);
    }

    private void showEditBatchForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int batchId = Integer.parseInt(request.getParameter("id"));
        Batch batch = batchDAO.getBatchById(batchId);
        List<Department> departments = departmentDAO.getAllDepartments();
        System.out.println(departments);
        request.setAttribute("batch", batch);
        request.setAttribute("departments", departments);
        request.getRequestDispatcher("/aeditBatch.jsp").forward(request, response);
    }

    private void addBatch(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String batchName = request.getParameter("batchName");
        int deptId = Integer.parseInt(request.getParameter("deptId"));
        String academicYear = request.getParameter("academicYear");
        
        Batch batch = new Batch();
        batch.setBatchName(batchName);
        batch.setDeptId(deptId);
        batch.setAcademicYear(academicYear);
        
        if (batchDAO.addBatch(batch)) {
            request.setAttribute("message", "Batch added successfully!");
        } else {
            request.setAttribute("error", "Failed to add batch.");
        }
        
        listBatches(request, response);
    }

    private void updateBatch(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int batchId = Integer.parseInt(request.getParameter("batchId"));
        String batchName = request.getParameter("batchName");
        int deptId = Integer.parseInt(request.getParameter("deptId"));
        String academicYear = request.getParameter("academicYear");
        
        Batch batch = new Batch();
        batch.setBatchId(batchId);
        batch.setBatchName(batchName);
        batch.setDeptId(deptId);
        batch.setAcademicYear(academicYear);
        
        if (batchDAO.updateBatch(batch)) {
            request.setAttribute("message", "Batch updated successfully!");
        } else {
            request.setAttribute("error", "Failed to update batch.");
        }
        
        listBatches(request, response);
    }

    private void deleteBatch(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int batchId = Integer.parseInt(request.getParameter("id"));
        System.out.println("In deletebatch");
        if (batchDAO.deleteBatch(batchId)) {
            request.setAttribute("message", "Batch deleted successfully!");
        } else {
            request.setAttribute("error", "Failed to delete batch.");
        }
        
        listBatches(request, response);
    }

    // Classroom related methods
    private void listClassrooms(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Classroom> classrooms = classroomDAO.getAllClassrooms();
        request.setAttribute("classrooms", classrooms);
        request.getRequestDispatcher("/aclassrooms.jsp").forward(request, response);
    }

    private void showAddClassroomForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/aaddClassroom.jsp").forward(request, response);
    }

    private void showEditClassroomForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int roomId = Integer.parseInt(request.getParameter("id"));
        Classroom classroom = classroomDAO.getClassroomById(roomId);
        request.setAttribute("classroom", classroom);
        request.getRequestDispatcher("/aeditClassroom.jsp").forward(request, response);
    }

    private void addClassroom(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String roomNumber = request.getParameter("roomNumber");
        String building = request.getParameter("building");
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        
        Classroom classroom = new Classroom();
        classroom.setRoomNumber(roomNumber);
        classroom.setBuilding(building);
        classroom.setCapacity(capacity);
        
        if (classroomDAO.addClassroom(classroom)) {
            request.setAttribute("message", "Classroom added successfully!");
        } else {
            request.setAttribute("error", "Failed to add classroom.");
        }
        
        listClassrooms(request, response);
    }

    private void updateClassroom(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        String roomNumber = request.getParameter("roomNumber");
        String building = request.getParameter("building");
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        
        Classroom classroom = new Classroom();
        classroom.setRoomId(roomId);
        classroom.setRoomNumber(roomNumber);
        classroom.setBuilding(building);
        classroom.setCapacity(capacity);
        
        if (classroomDAO.updateClassroom(classroom)) {
            request.setAttribute("message", "Classroom updated successfully!");
        } else {
            request.setAttribute("error", "Failed to update classroom.");
        }
        
        listClassrooms(request, response);
    }

    private void deleteClassroom(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int roomId = Integer.parseInt(request.getParameter("id"));
        
        if (classroomDAO.deleteClassroom(roomId)) {
            request.setAttribute("message", "Classroom deleted successfully!");
        } else {
            request.setAttribute("error", "Failed to delete classroom.");
        }
        
        listClassrooms(request, response);
    }

    // Timetable Slot related methods
    private void listSlots(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<TimetableSlot> slots = slotDAO.getAllSlots();
        request.setAttribute("slots", slots);
        request.getRequestDispatcher("/aslots.jsp").forward(request, response);
    }

    private void showAddSlotForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/aaddSlot.jsp").forward(request, response);
    }

    private void showEditSlotForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int slotId = Integer.parseInt(request.getParameter("id"));
        TimetableSlot slot = slotDAO.getSlotById(slotId);
        request.setAttribute("slot", slot);
        request.getRequestDispatcher("/aeditSlot.jsp").forward(request, response);
    }

    private void addSlot(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String dayOfWeek = request.getParameter("dayOfWeek");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        
        TimetableSlot slot = new TimetableSlot();
        slot.setDayOfWeek(dayOfWeek);
        slot.setStartTime(Time.valueOf(startTime + ":00"));
        slot.setEndTime(Time.valueOf(endTime + ":00"));
        
        if (slotDAO.addTimetableSlot(slot)) {
            request.setAttribute("message", "Time slot added successfully!");
        } else {
            request.setAttribute("error", "Failed to add time slot.");
        }
        
        listSlots(request, response);
    }

    private void updateSlot(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int slotId = Integer.parseInt(request.getParameter("slotId"));
        String dayOfWeek = request.getParameter("dayOfWeek");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        
        TimetableSlot slot = new TimetableSlot();
        slot.setSlotId(slotId);
        slot.setDayOfWeek(dayOfWeek);
        slot.setStartTime(Time.valueOf(startTime + ":00"));
        slot.setEndTime(Time.valueOf(endTime + ":00"));
        
        if (slotDAO.updateTimetableSlot(slot)) {
            request.setAttribute("message", "Time slot updated successfully!");
        } else {
            request.setAttribute("error", "Failed to update time slot.");
        }
        
        listSlots(request, response);
    }

    private void deleteSlot(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int slotId = Integer.parseInt(request.getParameter("id"));
        
        if (slotDAO.deleteTimetableSlot(slotId)) {
            request.setAttribute("message", "Time slot deleted successfully!");
        } else {
            request.setAttribute("error", "Failed to delete time slot.");
        }
        
        listSlots(request, response);
    }

    // User related methods
    private void listUsers(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<User> users = userDAO.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/ausers.jsp").forward(request, response);
    }

    private void showAddUserForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/aaddUser.jsp").forward(request, response);
    }

    private void showEditUserForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = userDAO.getUserById(userId);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/aeditUser.jsp").forward(request, response);
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(role);
        
        if (userDAO.addUser(user)) {
            request.setAttribute("message", "User added successfully!");
        } else {
            request.setAttribute("error", "Failed to add user.");
        }
        
        listUsers(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        
        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setEmail(email);
        user.setRole(role);
        
        if (userDAO.updateUser(user)) {
            request.setAttribute("message", "User updated successfully!");
        } else {
            request.setAttribute("error", "Failed to update user.");
        }
        
        listUsers(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        
        if (userDAO.deleteUser(userId)) {
            request.setAttribute("message", "User deleted successfully!");
        } else {
            request.setAttribute("error", "Failed to delete user.");
        }
        
        listUsers(request, response);
    }
    
    private void showEditTeacherForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int teacherId = Integer.parseInt(request.getParameter("id"));
        Teacher teacher = teacherDAO.getTeacherById(teacherId);
        List<Department> departments = departmentDAO.getAllDepartments();
        List<User> users = userDAO.getAllUsers();
        
        request.setAttribute("teacher", teacher);
        request.setAttribute("departments", departments);
        request.setAttribute("users", users);
        request.getRequestDispatcher("/aeditTeacher.jsp").forward(request, response);
    }

    private void updateTeacher(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));
        String teacherName = request.getParameter("teacherName");
        int deptId = Integer.parseInt(request.getParameter("deptId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        
        Teacher teacher = new Teacher();
        teacher.setTeacherId(teacherId);
        teacher.setTeacherName(teacherName);
        teacher.setDeptId(deptId);
        teacher.setUserId(userId);
        
        if (teacherDAO.updateTeacher(teacher)) {
            request.setAttribute("message", "Teacher updated successfully!");
        } else {
            request.setAttribute("error", "Failed to update teacher.");
        }
        
        listTeachers(request, response);
    }

    private void deleteTeacher(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));
       
        if (teacherDAO.deleteTeacher(teacherId)) {
            request.setAttribute("message", "Teacher deleted successfully!");
        } else {
            request.setAttribute("error", "Failed to delete teacher.");
        }
        
        listTeachers(request, response);
    }
    
    
}
package com.timetable.controller;

import com.timetable.dao.UserDAO;
import com.timetable.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/user/*")
public class UserController extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        userDAO = new UserDAO();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getPathInfo();
        System.out.println("In get");
        
        if (action == null) {
            action = "/login";
        }
        
        try {
            switch (action) {
                case "/login":
                    showLoginForm(request, response);
                    break;
                case "/register":
                    showRegisterForm(request, response);
                    break;
                case "/logout":
                    logout(request, response);
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
        
        System.out.println("in post");
        
        
        if (action == null) {
            action = "/login";
        }
        
        try {
            switch (action) {
                case "/login":
                    login(request, response);
                    break;
                case "/register":
                    register(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    
    private void showLoginForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
    }
    
    private void showRegisterForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/auth/register.jsp").forward(request, response);
    }
    
    private void login(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("JE;;p");
        User user = userDAO.getUserByUsername(username);
        
        if (user != null && user.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            
            if ("admin".equals(user.getRole())) {
            	System.out.println("hekki");
                response.sendRedirect(request.getContextPath() + "/adashboard.jsp");
            } else {
            	System.out.println("Hello");
                response.sendRedirect(request.getContextPath() + "/tdashboard.jsp");
            }
        } else {
            request.setAttribute("error", "Invalid username or password");
            showLoginForm(request, response);
        }
    }
    
    private void register(HttpServletRequest request, HttpServletResponse response) 
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
            request.setAttribute("message", "Registration successful! Please login.");
            showLoginForm(request, response);
        } else {
            request.setAttribute("error", "Registration failed. Username or email may already exist.");
            showRegisterForm(request, response);
        }
    }
    
    private void logout(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
    }
}
package com.timetable.util;

import com.timetable.model.User;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AuthenticationFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code if needed
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        
        String requestURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        
        // Allow access to login, register, and static resources without authentication
        if (requestURI.startsWith(contextPath + "/auth/") || 
            requestURI.startsWith(contextPath + "/css/") || 
            requestURI.startsWith(contextPath + "/js/") || 
            requestURI.equals(contextPath + "/")) {
            chain.doFilter(request, response);
            return;
        }
        
        boolean loggedIn = (session != null && session.getAttribute("user") != null);
        
        if (!loggedIn) {
            httpResponse.sendRedirect(contextPath + "/auth/login.jsp");
            return;
        }
        
        // Check role-based access
        User user = (User) session.getAttribute("user");
        if (requestURI.startsWith(contextPath + "/admin/") && !"admin".equals(user.getRole())) {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            return;
        }
        
        if (requestURI.startsWith(contextPath + "/teacher/") && !"teacher".equals(user.getRole())) {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            return;
        }
        
        if (requestURI.startsWith(contextPath + "/student/") && !"student".equals(user.getRole())) {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            return;
        }
        
        chain.doFilter(request, response);
    }
    
    @Override
    public void destroy() {
        // Cleanup code if needed
    }
}
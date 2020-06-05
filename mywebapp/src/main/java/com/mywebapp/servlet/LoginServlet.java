package com.mywebapp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mywebapp.dao.LoginDao;
import com.mywebapp.dao.UserDao;

import model.User;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet(value="/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    UserDao userDao = new UserDao();
    LoginDao loginDao = new LoginDao();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// http://localhost:8080/mywebapp/login?action=login
			if(request.getParameter("action").equals("login")) {
				// Check if already logged
				HttpSession session = request.getSession();
				if(session.getAttribute("loggedEmail") != null) {
					// Get the list of logins
					request.setAttribute("loginList", loginDao.findAllList());
				}
				
				request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
			}
			else {
				if(request.getParameter("action").equals("remove")) {
					// Remove the Login
					loginDao.remove(request.getParameter("id"));
					response.sendRedirect(request.getContextPath() + "/login?action=login");
				}
				else {
					// error
					response.sendRedirect(request.getContextPath() + "/error");
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/error");
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getParameter("action").equals("login")) {
			// Check if already logged
			HttpSession session = request.getSession();
			if(session.getAttribute("email") != null) {
				// already logged
				System.out.println("Login : already logged");
				// tbd
			}
			else {
				// Check if the login is correct
				System.out.println(request.getParameter("email") + " - " + request.getParameter("password"));
				//if(login ok -> log on)
				if(loginDao.checkLogin(request.getParameter("email"), request.getParameter("password"))) {
					// login OK -> set login in session
					System.out.println("Login : " + request.getParameter("email") + " logged");
					session.setAttribute("loggedEmail", request.getParameter("email"));
					response.sendRedirect(request.getContextPath() + "/userdetail?action=list");
				}
				else {
					// login NOK
					System.out.println("Login unauthorized");
					response.sendRedirect(request.getContextPath() + "/login?action=login");
				}
			}
		}
		else {
			if(request.getParameter("action").equals("create")) {
				// Create
				//System.out.println(request.getParameter("email") + " - "+ request.getParameter("password"));
				loginDao.create(request.getParameter("email"), request.getParameter("password"));
				response.sendRedirect(request.getContextPath() + "/login?action=login");
			}
			else{
				if(request.getParameter("action").equals("logout")) {
					// Logout
					HttpSession session = request.getSession();
					session.removeAttribute("loggedEmail"); // just to be sure
					session.invalidate();
					response.sendRedirect(request.getContextPath() + "/login?action=login");
				}
			}
		}

		//doGet(request, response);
	}

}

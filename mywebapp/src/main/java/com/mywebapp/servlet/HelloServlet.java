package com.mywebapp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mywebapp.dao.UserDao;

import model.User;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet(value="/userdetail")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    UserDao userDao = new UserDao();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//version 1
		//request.setAttribute("name", "Jean-Claude");
		
		//version 2
		//User u = new User("Tom", "White", 50000);
		//request.setAttribute("user", u);
		
		//version 3
		/*
		String idParam = request.getParameter("id");
		UserDao userDao = new UserDao();
		User u = userDao.findUser(idParam);
		request.setAttribute("user", u);
		*/
		
		System.out.println("PARAM = "+ request.getParameter("id"));
		
		//version 4
		
		try {
			// http://localhost:8080/mywebapp/userdetail?action=detail&id=John
			if(request.getParameter("action").equals("detail")) {
				if(request.getParameter("id") != null) {
					String idParam = request.getParameter("id");
					User u = userDao.findUser(idParam);
					request.setAttribute("user", u);
					
					request.getRequestDispatcher("/WEB-INF/pages/user-detail.jsp").forward(request, response);
				}
			}
			else {
				// http://localhost:8080/mywebapp/userdetail?action=list
				if(request.getParameter("action").equals("list")) {
					request.setAttribute("userList", userDao.findAllList());
					request.getRequestDispatcher("/WEB-INF/pages/user-list.jsp").forward(request, response);
				}
				else {
					if(request.getParameter("action").equals("add")) {
						//request.setAttribute("userList", userDao.findAllList());
						request.getRequestDispatcher("/WEB-INF/pages/user-add.jsp").forward(request, response);
					}
					else {
						// error
						response.sendRedirect(request.getContextPath() + "/error");
					}
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
		System.out.println("Hello from POST " + request.getParameter("lastame"));
		//response.getWriter().append("Hello from POST ").append(request.getParameter("theName")).append(" from POST ");
		User user = new User(request.getParameter("firstname"), request.getParameter("lastname"), Double.valueOf(request.getParameter("salary")));
		userDao.addUser(request.getParameter("firstname"), user);
		response.sendRedirect(request.getContextPath() + "/userdetail?action=list");
		//doGet(request, response);
	}

}

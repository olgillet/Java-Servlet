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
@WebServlet(value="/another/servlet")
public class AnotherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnotherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    UserDao userDao = new UserDao();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Just to play with parameter
		request.setAttribute("postUserUrl", "/thebadurl");
		// request.getServletContext().getInitParameter("postUserUrl")  : does not retrieve the value from web.xml
		System.out.println("Application postUserUrl = " + request.getServletContext().getInitParameter("postUserUrl"));
		//request.getServletContext().setAttribute("postUserUrl", request.getServletContext().getInitParameter("postUserUrl"));
		// so manually
		request.getServletContext().setAttribute("postUserUrl", "/another/very/bad/url");
		
		// the correct redirection
		request.getRequestDispatcher("/WEB-INF/pages/user-add.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hello from POST " + request.getParameter("lastame"));
		User user = new User(request.getParameter("firstname"), request.getParameter("lastname"), Double.valueOf(request.getParameter("salary")));
		userDao.addUser(request.getParameter("firstname"), user);
		response.sendRedirect(request.getContextPath() + "/userdetail?action=list");
	}
	**/

}

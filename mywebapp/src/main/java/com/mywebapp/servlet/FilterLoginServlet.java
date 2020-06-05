package com.mywebapp.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FilterLoginServlet implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("************ Filter ****************");
		String path = ((HttpServletRequest)request).getRequestURI();
		if(path.contains("/error")) {
			chain.doFilter(request, response); // let's continue, only for error
		}
		else {
			if(request instanceof HttpServletRequest) {
				HttpSession session = ((HttpServletRequest)request).getSession();
				if(session.getAttribute("loggedEmail") != null) {
					chain.doFilter(request, response);
				}
				else {
					System.out.println("Not logged redirection by Filter");
					((HttpServletResponse)response).sendRedirect( ((HttpServletRequest)request).getContextPath() + "error?message=notLogged" );
				}
			}
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}

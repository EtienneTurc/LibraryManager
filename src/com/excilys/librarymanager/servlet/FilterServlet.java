package com.excilys.librarymanager.servlet;

import javax.servlet.http.*;

import javax.servlet.*;

/**
 * FilterServlet
 */
public class FilterServlet extends HttpServlet {

	private static final long serialVersionUID = 3150141574075233376L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

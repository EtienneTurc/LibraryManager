package com.excilys.librarymanager.servlet;

import javax.servlet.http.*;

import javax.servlet.*;

import com.excilys.librarymanager.exception.ServiceException;

import com.excilys.librarymanager.services.MemberServiceImpl;

/**
 * MemberListServlet
 */
public class MemberListServlet extends HttpServlet {

	private static final long serialVersionUID = 495725735793181184L;

	public MemberListServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/View/member_list.jsp");

		try {
			MemberServiceImpl member_service = MemberServiceImpl.getInstance();

			request.setAttribute("members", member_service.getAll());

			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			throw new ServletException(e);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

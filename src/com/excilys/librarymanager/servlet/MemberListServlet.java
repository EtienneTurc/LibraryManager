package com.excilys.librarymanager.servlet;

import javax.servlet.http.*;

import javax.servlet.*;

import com.excilys.librarymanager.services.MemberServiceImpl;

/**
 * MemberListServlet
 */
public class MemberListServlet extends HttpServlet {

	public MemberListServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/View/member_list.jsp");

		try {
			MemberServiceImpl member_service = MemberServiceImpl.getInstance();

			request.setAttribute("members", member_service.getAll());

			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

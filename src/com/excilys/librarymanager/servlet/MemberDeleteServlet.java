package com.excilys.librarymanager.servlet;

import javax.servlet.http.*;

import javax.servlet.*;

import com.excilys.librarymanager.services.MemberServiceImpl;

/**
 * MemberDeleteServlet
 */
public class MemberDeleteServlet extends HttpServlet {

	public MemberDeleteServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/View/member_delete.jsp");

		try {
			MemberServiceImpl member_service = MemberServiceImpl.getInstance();
			String id = request.getParameter("id");
			request.setAttribute("member", member_service.getById(Integer.parseInt(id)));
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			MemberServiceImpl member_service = MemberServiceImpl.getInstance();
			String id = request.getParameter("id");
			member_service.delete(Integer.parseInt(id));

			response.sendRedirect("member_list");

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

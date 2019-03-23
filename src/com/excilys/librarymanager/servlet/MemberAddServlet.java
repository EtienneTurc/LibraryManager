package com.excilys.librarymanager.servlet;

import javax.servlet.http.*;
import javax.servlet.*;

import com.excilys.librarymanager.services.MemberServiceImpl;

/**
 * MemberAddServlet
 */
public class MemberAddServlet extends HttpServlet {

	public MemberAddServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/View/member_add.jsp");

		try {
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");

			MemberServiceImpl member_service = MemberServiceImpl.getInstance();
			String last_name = request.getParameter("last_name");
			String first_name = request.getParameter("first_name");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");
			String mail = request.getParameter("mail");
			int id = member_service.create(last_name, first_name, address, mail, phone);

			response.sendRedirect("member_details?id=" + id);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

package com.excilys.librarymanager.servlet;

import javax.servlet.http.*;

import javax.servlet.*;

import com.excilys.librarymanager.exception.ServiceException;

import com.excilys.librarymanager.services.MemberServiceImpl;
import com.excilys.librarymanager.services.BorrowServiceImpl;
import com.excilys.librarymanager.models.Member;

/**
 * MemberDetailsServlet
 */
public class MemberDetailsServlet extends HttpServlet {

	private static final long serialVersionUID = -7064993961085035232L;

	public MemberDetailsServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/View/member_details.jsp");

		try {
			MemberServiceImpl member_service = MemberServiceImpl.getInstance();
			BorrowServiceImpl borrow_service = BorrowServiceImpl.getInstance();

			String id = request.getParameter("id");
			request.setAttribute("member", member_service.getById(Integer.parseInt(id)));
			request.setAttribute("borrows", borrow_service.getListCurrentByMember(Integer.parseInt(id)));

			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("wtf");
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			request.setCharacterEncoding("UTF-8");

			MemberServiceImpl member_service = MemberServiceImpl.getInstance();
			String id = request.getParameter("id");
			String first_name = request.getParameter("first_name");
			String last_name = request.getParameter("last_name");
			String address = request.getParameter("address");
			String mail = request.getParameter("mail");
			String phone = request.getParameter("phone");
			String subs = request.getParameter("subs");
			Member m = new Member(Integer.parseInt(id), last_name, first_name, address, mail, phone, subs);
			member_service.update(m);

			doGet(request, response);
		} catch (ServiceException e) {
			throw new ServletException(e);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

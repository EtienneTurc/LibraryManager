package com.excilys.librarymanager.servlet;

import javax.servlet.http.*;
import javax.servlet.*;

import com.excilys.librarymanager.services.BookServiceImpl;
import com.excilys.librarymanager.services.MemberServiceImpl;
import com.excilys.librarymanager.services.BorrowServiceImpl;

/**
 * DashboardServlet
 */
public class DashboardServlet extends HttpServlet {

	private static final long serialVersionUID = 1924560675656000322L;

	public DashboardServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/View/dashboard.jsp");

		try {
			BookServiceImpl book_service = BookServiceImpl.getInstance();
			MemberServiceImpl member_service = MemberServiceImpl.getInstance();
			BorrowServiceImpl borrow_service = BorrowServiceImpl.getInstance();

			request.setAttribute("book_count", book_service.count());
			request.setAttribute("member_count", member_service.count());
			request.setAttribute("borrow_count", borrow_service.count());
			request.setAttribute("current_borrows", borrow_service.getListCurrent());
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

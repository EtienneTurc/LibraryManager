package com.excilys.librarymanager.servlet;

import javax.servlet.http.*;

import javax.servlet.*;

import com.excilys.librarymanager.services.BorrowServiceImpl;

/**
 * BorrowReturnServlet
 */
public class BorrowReturnServlet extends HttpServlet {
	private static final long serialVersionUID = 5033575590481652963L;

	public BorrowReturnServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/View/borrow_return.jsp");

		try {

			BorrowServiceImpl borrow_service = BorrowServiceImpl.getInstance();

			request.setAttribute("id", request.getParameter("id"));
			request.setAttribute("borrows", borrow_service.getListCurrent());

			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			BorrowServiceImpl borrow_service = BorrowServiceImpl.getInstance();
			String id = request.getParameter("idBorrow");
			borrow_service.returnBook(Integer.parseInt(id));
			response.sendRedirect("borrow_list");

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

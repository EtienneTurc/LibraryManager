package com.excilys.librarymanager.servlet;

import javax.servlet.http.*;

import javax.servlet.*;

import com.excilys.librarymanager.exception.ServiceException;

import com.excilys.librarymanager.services.BorrowServiceImpl;

/**
 * BorrowReturnServlet
 */
public class BorrowReturnServlet extends HttpServlet {

	private static final long serialVersionUID = 5033575590481652963L;

	public BorrowReturnServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/View/borrow_return.jsp");

		try {

			BorrowServiceImpl borrow_service = BorrowServiceImpl.getInstance();

			request.setAttribute("id", request.getParameter("id"));
			request.setAttribute("borrows", borrow_service.getListCurrent());

			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			throw new ServletException(e);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			BorrowServiceImpl borrow_service = BorrowServiceImpl.getInstance();
			String id = request.getParameter("idBorrow");
			borrow_service.returnBook(Integer.parseInt(id));
			response.sendRedirect("borrow_list");

		} catch (ServiceException e) {
			throw new ServletException(e);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

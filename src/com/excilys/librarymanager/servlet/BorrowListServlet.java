package com.excilys.librarymanager.servlet;

import javax.servlet.http.*;

import javax.servlet.*;

import com.excilys.librarymanager.services.BorrowServiceImpl;

/**
 * BorrowListServlet
 */
public class BorrowListServlet extends HttpServlet {

	public BorrowListServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/View/borrow_list.jsp");

		try {
			BorrowServiceImpl borrow_service = BorrowServiceImpl.getInstance();

			String show = request.getParameter("show");

			if (show == "all") {
				request.setAttribute("borrows", borrow_service.getAll());
			} else {
				request.setAttribute("borrows", borrow_service.getListCurrent());
			}

			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

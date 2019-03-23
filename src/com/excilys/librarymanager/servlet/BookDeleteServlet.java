package com.excilys.librarymanager.servlet;

import javax.servlet.http.*;

import javax.servlet.*;

import com.excilys.librarymanager.services.BookServiceImpl;

/**
 * BookDeleteServlet
 */
public class BookDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 2617576053345537206L;

	public BookDeleteServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/View/book_delete.jsp");

		try {
			BookServiceImpl book_service = BookServiceImpl.getInstance();
			String id = request.getParameter("id");
			request.setAttribute("book", book_service.getById(Integer.parseInt(id)));
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			BookServiceImpl book_service = BookServiceImpl.getInstance();
			String id = request.getParameter("id");
			book_service.delete(Integer.parseInt(id));

			response.sendRedirect("book_list");

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

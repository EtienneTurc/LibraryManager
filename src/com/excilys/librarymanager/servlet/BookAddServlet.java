package com.excilys.librarymanager.servlet;

import javax.servlet.http.*;
import javax.servlet.*;

import com.excilys.librarymanager.services.BookServiceImpl;

/**
 * BookAddServlet
 */
public class BookAddServlet extends HttpServlet {

	private static final long serialVersionUID = 7207818440455437378L;

	public BookAddServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/View/book_add.jsp");

		try {
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			BookServiceImpl book_service = BookServiceImpl.getInstance();
			String title = request.getParameter("title");
			String author = request.getParameter("author");
			String isbn = request.getParameter("isbn");
			int id = book_service.create(title, author, isbn);

			response.sendRedirect("book_details?id=" + id);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

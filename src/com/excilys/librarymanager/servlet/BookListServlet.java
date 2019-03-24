package com.excilys.librarymanager.servlet;

import javax.servlet.http.*;

import javax.servlet.*;

import com.excilys.librarymanager.exception.ServiceException;

import com.excilys.librarymanager.services.BookServiceImpl;

/**
 * BookListServlet
 */
public class BookListServlet extends HttpServlet {

	private static final long serialVersionUID = 3139380603080067921L;

	public BookListServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/View/book_list.jsp");

		try {
			BookServiceImpl book_service = BookServiceImpl.getInstance();

			request.setAttribute("books", book_service.getAll());

			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			throw new ServletException(e);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

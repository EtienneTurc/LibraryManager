package com.excilys.librarymanager.servlet;

import javax.servlet.http.*;

import javax.servlet.*;

import com.excilys.librarymanager.services.BookServiceImpl;
import com.excilys.librarymanager.services.BorrowServiceImpl;
import com.excilys.librarymanager.models.Book;

/**
 * BookDetailsServlet
 */
public class BookDetailsServlet extends HttpServlet {

	private static final long serialVersionUID = -5890361601468711373L;

	public BookDetailsServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/View/book_details.jsp");

		try {
			BookServiceImpl book_service = BookServiceImpl.getInstance();
			BorrowServiceImpl borrow_service = BorrowServiceImpl.getInstance();

			String id = request.getParameter("id");
			request.setAttribute("book", book_service.getById(Integer.parseInt(id)));
			request.setAttribute("borrows", borrow_service.getListCurrentByBook(Integer.parseInt(id)));

			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			BookServiceImpl book_service = BookServiceImpl.getInstance();
			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String author = request.getParameter("author");
			String isbn = request.getParameter("isbn");
			Book b = new Book(Integer.parseInt(id), title, author, isbn);
			book_service.update(b);

			doGet(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

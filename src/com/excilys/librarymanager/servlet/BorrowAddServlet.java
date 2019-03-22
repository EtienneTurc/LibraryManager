package com.excilys.librarymanager.servlet;

import javax.servlet.http.*;

import java.time.LocalDate;

import javax.servlet.*;

import com.excilys.librarymanager.services.BookServiceImpl;
import com.excilys.librarymanager.services.MemberServiceImpl;
import com.excilys.librarymanager.services.BorrowServiceImpl;
import com.excilys.librarymanager.models.Borrow;

/**
 * BorrowAddServlet
 */
public class BorrowAddServlet extends HttpServlet {

	public BorrowAddServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/View/borrow_add.jsp");

		try {
			BookServiceImpl book_service = BookServiceImpl.getInstance();
			MemberServiceImpl member_service = MemberServiceImpl.getInstance();

			request.setAttribute("books_available", book_service.getBooksAvailable());
			request.setAttribute("members_available", member_service.getListMemberBorrowAvailable());
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			BorrowServiceImpl borrow_service = BorrowServiceImpl.getInstance();
			String id_book = request.getParameter("idBook");
			String id_member = request.getParameter("idMember");
			borrow_service.create(Integer.parseInt(id_member), Integer.parseInt(id_book), LocalDate.now());
			response.sendRedirect("dashboard");

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

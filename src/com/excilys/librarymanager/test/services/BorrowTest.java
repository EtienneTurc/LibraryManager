package com.excilys.librarymanager.test.services;

import java.util.List;

import com.excilys.librarymanager.services.BorrowServiceImpl;
import com.excilys.librarymanager.exception.ServiceException;

import com.excilys.librarymanager.models.Borrow;

/**
 * ServiceTest
 */
public class BorrowTest {
	public static void main(String[] args) {
		BorrowServiceImpl borrow_service = BorrowServiceImpl.getInstance();
		try {
			System.out.println("All");
			List<Borrow> all = borrow_service.getAll();
			for (Borrow b : all) {
				System.out.print(b.getId() + " ");
				System.out.print(b.getBook().getTitle() + " ");
				System.out.print(b.getBook().getAuthor() + " ");
				System.out.print(b.getBook().getId() + " ");
				System.out.println(b.getBook().getIsbn());
			}

			System.out.println("List current");
			List<Borrow> list_current = borrow_service.getListCurrent();
			for (Borrow b : list_current) {
				System.out.print(b.getId() + " ");
				System.out.print(b.getBook().getTitle() + " ");
				System.out.print(b.getBook().getAuthor() + " ");
				System.out.print(b.getBook().getId() + " ");
				System.out.println(b.getBook().getIsbn());
			}

			System.out.println("Current Member");
			List<Borrow> current_member = borrow_service.getListCurrentByMember(1);
			for (Borrow b : current_member) {
				System.out.print(b.getId() + " ");
				System.out.print(b.getBook().getTitle() + " ");
				System.out.print(b.getBook().getAuthor() + " ");
				System.out.print(b.getBook().getId() + " ");
				System.out.println(b.getBook().getIsbn());
			}

			System.out.println("Current Book");
			List<Borrow> current_book = borrow_service.getListCurrentByBook(3);
			for (Borrow b : current_book) {
				System.out.print(b.getId() + " ");
				System.out.print(b.getBook().getTitle() + " ");
				System.out.print(b.getBook().getAuthor() + " ");
				System.out.print(b.getBook().getId() + " ");
				System.out.println(b.getBook().getIsbn());
			}

			System.out.println("Borrow 1");
			Borrow b = borrow_service.getById(1);
			System.out.print(b.getId() + " ");
			System.out.print(b.getBook().getTitle() + " ");
			System.out.print(b.getBook().getAuthor() + " ");
			System.out.print(b.getBook().getId() + " ");
			System.out.println(b.getBook().getIsbn());

		} catch (ServiceException e) {
			System.out.println(e);
		}

	}
}

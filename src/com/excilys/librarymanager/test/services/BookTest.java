package com.excilys.librarymanager.test.services;

import java.util.List;

import com.excilys.librarymanager.services.BookServiceImpl;
import com.excilys.librarymanager.exception.ServiceException;

import com.excilys.librarymanager.models.Book;

/**
 * ServiceTest
 */
public class BookTest {
	public static void main(String[] args) {
		BookServiceImpl book_service = BookServiceImpl.getInstance();
		try {

			System.out.println("All books");
			List<Book> all = book_service.getAll();
			for (Book b : all) {
				System.out.print(b.getTitle() + " ");
				System.out.print(b.getAuthor() + " ");
				System.out.print(b.getId() + " ");
				System.out.println(b.getIsbn());
			}

			System.out.println("All available");
			List<Book> available = book_service.getBooksAvailable();
			for (Book b : available) {
				System.out.print(b.getTitle() + " ");
				System.out.print(b.getAuthor() + " ");
				System.out.print(b.getId() + " ");
				System.out.println(b.getIsbn());
			}

			System.out.println("Book 1");
			Book b = book_service.getById(1);
			System.out.print(b.getTitle() + " ");
			System.out.print(b.getAuthor() + " ");
			System.out.print(b.getId() + " ");
			System.out.println(b.getIsbn());

			// int id = book_service.create("Mon titre", "De moi", "123456789");
			// book_service.update(b);
			// book_service.delete(id);
			// int count = book_service.count();

		} catch (ServiceException e) {
			System.out.println(e);
		}

	}
}

package com.excilys.librarymanager.test.services;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.excilys.librarymanager.models.Book;

import com.excilys.librarymanager.services.BookServiceImpl;
import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.utils.FillDatabase;

/**
 * BookServiceTest
 */
public class BookServiceTest {
	private BookServiceImpl book_service;

	@Before
	public void setUp() {
		try {
			FillDatabase.fillDatabase();
			book_service = BookServiceImpl.getInstance();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Test
	public void getAllTest() {
		try {
			List<Book> books = book_service.getAll();

			Assert.assertEquals(1, books.get(0).getId());
			Assert.assertEquals("Les oiseaux migrateurs", books.get(0).getTitle());
			Assert.assertEquals(2, books.get(1).getId());
			Assert.assertEquals("Le génie des oiseaux", books.get(1).getTitle());
			Assert.assertEquals(3, books.get(2).getId());
			Assert.assertEquals("Dans la forêt", books.get(2).getTitle());
			Assert.assertEquals(4, books.get(3).getId());
			Assert.assertEquals("Les Séries télé pour les nuls", books.get(3).getTitle());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void getBooksAvailableTest() {
		try {
			List<Book> books = book_service.getBooksAvailable();

			Assert.assertEquals(1, books.get(0).getId());
			Assert.assertEquals("Les oiseaux migrateurs", books.get(0).getTitle());
			Assert.assertEquals(4, books.get(1).getId());
			Assert.assertEquals("Les Séries télé pour les nuls", books.get(1).getTitle());
			Assert.assertEquals(5, books.get(2).getId());
			Assert.assertEquals("L'univers des séries TV", books.get(2).getTitle());
			Assert.assertEquals(6, books.get(3).getId());
			Assert.assertEquals("Tartes pour tous", books.get(3).getTitle());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void getByIdTest() {
		try {
			Book book = book_service.getById(2);

			Assert.assertEquals(2, book.getId());
			Assert.assertEquals("Le génie des oiseaux", book.getTitle());
			Assert.assertEquals("Jennifer ACKERMAN", book.getAuthor());
			Assert.assertEquals("978-2501124881", book.getIsbn());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void createTest() {
		try {
			int id = book_service.create("My title", "My author", "42");
			Book book = book_service.getById(id);

			Assert.assertEquals(id, book.getId());
			Assert.assertEquals("My title", book.getTitle());
			Assert.assertEquals("My author", book.getAuthor());
			Assert.assertEquals("42", book.getIsbn());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void updateTest() {
		try {
			Book book = book_service.getById(2);
			book.setTitle("New title");
			book_service.update(book);
			book = book_service.getById(2);

			Assert.assertEquals(2, book.getId());
			Assert.assertEquals("New title", book.getTitle());
			Assert.assertEquals("Jennifer ACKERMAN", book.getAuthor());
			Assert.assertEquals("978-2501124881", book.getIsbn());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void deleteTest() {
		try {
			book_service.delete(2);
			book_service.getById(2);
		} catch (ServiceException e) {
			Assert.assertEquals("Not found", e.getMessage());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void countTest() {
		try {
			int c = book_service.count();
			Assert.assertEquals(10, c);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

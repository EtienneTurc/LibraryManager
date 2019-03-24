package com.excilys.librarymanager.test.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.excilys.librarymanager.models.Book;

import com.excilys.librarymanager.dao.BookDaoImpl;
import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.utils.FillDatabase;

/**
 * BookDaoTest
 */
public class BookDaoTest {
	private BookDaoImpl book_dao;

	@Before
	public void setUp() {
		try {
			FillDatabase.fillDatabase();
			book_dao = BookDaoImpl.getInstance();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Test
	public void getAllTest() {
		try {
			List<Book> books = book_dao.getAll();

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
	public void getByIdTest() {
		try {
			Book book = book_dao.getById(2);

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
			int id = book_dao.create("My title", "My author", "42");
			Book book = book_dao.getById(id);

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
			Book book = book_dao.getById(2);
			book.setTitle("New title");
			book_dao.update(book);
			book = book_dao.getById(2);

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
			book_dao.delete(2);
			book_dao.getById(2);
		} catch (DaoException e) {
			Assert.assertEquals("Not found", e.getMessage());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void countTest() {
		try {
			int c = book_dao.count();
			Assert.assertEquals(10, c);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

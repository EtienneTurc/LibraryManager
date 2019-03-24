package com.excilys.librarymanager.test.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.excilys.librarymanager.models.Book;

/**
 * BookModelTest
 */
public class BookModelTest {
	private Book book;

	@Before
	public void setUp() {
		book = new Book(1, "Hello", "me ME", "42");
	}

	@Test
	public void getIdTest() {
		Assert.assertEquals(1, book.getId());
	}

	@Test
	public void getTitleTest() {
		Assert.assertEquals("Hello", book.getTitle());
	}

	@Test
	public void getAuthorTest() {
		Assert.assertEquals("me ME", book.getAuthor());
	}

	@Test
	public void getIsbnTest() {
		Assert.assertEquals("42", book.getIsbn());
	}

	@Test
	public void setTitleTest() {
		book.setTitle("Other title");
		Assert.assertEquals("Other title", book.getTitle());
	}

	@Test
	public void setAuthorTest() {
		book.setAuthor("Other author");
		Assert.assertEquals("Other author", book.getAuthor());
	}

	@Test
	public void setIsbnTest() {
		book.setIsbn("4224");
		Assert.assertEquals("4224", book.getIsbn());
	}
}

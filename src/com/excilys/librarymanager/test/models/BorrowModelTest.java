package com.excilys.librarymanager.test.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.excilys.librarymanager.models.Borrow;

import java.time.LocalDate;

import com.excilys.librarymanager.models.Book;
import com.excilys.librarymanager.models.Member;

/**
 * BorrowModelTest
 */
public class BorrowModelTest {
	private Borrow borrow;
	private Member m;
	private Book b;

	@Before
	public void setUp() {
		m = new Member(1, "TURC", "Etienne", "Palaiseau", "moi@moi", "06", "BASIC");
		b = new Book(1, "Hello", "me ME", "42");
		borrow = new Borrow(1, m, b, LocalDate.of(2000, 04, 01), null);
	}

	@Test
	public void getIdTest() {
		Assert.assertEquals(1, borrow.getId());
	}

	@Test
	public void getMemberTest() {
		Assert.assertEquals(m, borrow.getMember());
	}

	@Test
	public void getBookTest() {
		Assert.assertEquals(b, borrow.getBook());
	}

	@Test
	public void getStartBorrowTest() {
		Assert.assertEquals(LocalDate.of(2000, 04, 01), borrow.getStartBorrow());
	}

	@Test
	public void getEndBorrowTest() {
		Assert.assertEquals(null, borrow.getEndBorrow());
	}
}

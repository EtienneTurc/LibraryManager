package com.excilys.librarymanager.test.dao;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.excilys.librarymanager.models.Borrow;

import com.excilys.librarymanager.dao.BorrowDaoImpl;
import com.excilys.librarymanager.utils.FillDatabase;

/**
 * BorrowDaoTest
 */
public class BorrowDaoTest {
	private BorrowDaoImpl borrow_dao;

	@Before
	public void setUp() {
		try {
			FillDatabase.fillDatabase();
			borrow_dao = BorrowDaoImpl.getInstance();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Test
	public void getAllTest() {
		try {
			List<Borrow> borrows = borrow_dao.getAll();

			Assert.assertEquals(2, borrows.get(0).getId());
			Assert.assertEquals("Le génie des oiseaux", borrows.get(0).getBook().getTitle());
			Assert.assertEquals(4, borrows.get(1).getId());
			Assert.assertEquals("Catalogue vasarely/partage des formes/catalogue de l'exposition",
					borrows.get(1).getBook().getTitle());
			Assert.assertEquals(1, borrows.get(2).getId());
			Assert.assertEquals("Les oiseaux migrateurs", borrows.get(2).getBook().getTitle());
			Assert.assertEquals(3, borrows.get(3).getId());
			Assert.assertEquals("Dans la forêt", borrows.get(3).getBook().getTitle());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void getListCurrentTest() {
		try {
			List<Borrow> borrows = borrow_dao.getListCurrent();

			Assert.assertEquals(3, borrows.get(0).getId());
			Assert.assertEquals("Dans la forêt", borrows.get(0).getBook().getTitle());
			Assert.assertEquals(5, borrows.get(1).getId());
			Assert.assertEquals("Le génie des oiseaux", borrows.get(1).getBook().getTitle());
			Assert.assertEquals(6, borrows.get(2).getId());
			Assert.assertEquals("M, le bord de l'abîme", borrows.get(2).getBook().getTitle());

			Assert.assertEquals(3, borrows.size());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void getListCurrentByMemberTest() {
		try {
			List<Borrow> borrows = borrow_dao.getListCurrentByMember(5);

			Assert.assertEquals(3, borrows.get(0).getId());
			Assert.assertEquals("Dans la forêt", borrows.get(0).getBook().getTitle());
			Assert.assertEquals(5, borrows.get(1).getId());
			Assert.assertEquals("Le génie des oiseaux", borrows.get(1).getBook().getTitle());

			Assert.assertEquals(2, borrows.size());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void getListCurrentByBookTest() {
		try {
			List<Borrow> borrows = borrow_dao.getListCurrentByBook(3);

			Assert.assertEquals(3, borrows.get(0).getId());
			Assert.assertEquals("MARLEAU", borrows.get(0).getMember().getLastName());

			Assert.assertEquals(1, borrows.size());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void getByIdTest() {
		try {
			Borrow borrow = borrow_dao.getById(3);

			Assert.assertEquals(3, borrow.getId());
			Assert.assertEquals("Dans la forêt", borrow.getBook().getTitle());
			Assert.assertEquals("MARLEAU", borrow.getMember().getLastName());
			Assert.assertEquals(LocalDate.of(2019, 03, 01), borrow.getStartBorrow());
			Assert.assertEquals(null, borrow.getEndBorrow());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void createTest() {
		try {

			int id = borrow_dao.create(5, 1, LocalDate.of(2000, 04, 01));
			Borrow borrow = borrow_dao.getById(id);

			Assert.assertEquals(id, borrow.getId());
			Assert.assertEquals("Les oiseaux migrateurs", borrow.getBook().getTitle());
			Assert.assertEquals("MARLEAU", borrow.getMember().getLastName());
			Assert.assertEquals(LocalDate.of(2000, 04, 01), borrow.getStartBorrow());
			Assert.assertEquals(null, borrow.getEndBorrow());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void updateTest() {
		try {
			Borrow borrow = borrow_dao.getById(3);
			borrow.setEndBorrow(LocalDate.of(2020, 04, 01));
			borrow_dao.update(borrow);
			borrow = borrow_dao.getById(3);

			Assert.assertEquals(3, borrow.getId());
			Assert.assertEquals("Dans la forêt", borrow.getBook().getTitle());
			Assert.assertEquals("MARLEAU", borrow.getMember().getLastName());
			Assert.assertEquals(LocalDate.of(2019, 03, 01), borrow.getStartBorrow());
			Assert.assertEquals(LocalDate.of(2020, 04, 01), borrow.getEndBorrow());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void countTest() {
		try {
			int c = borrow_dao.count();
			Assert.assertEquals(6, c);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

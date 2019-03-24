package com.excilys.librarymanager.test.services;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.excilys.librarymanager.models.Member;

import com.excilys.librarymanager.services.MemberServiceImpl;
import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.utils.FillDatabase;

/**
 * MemberServiceTest
 */
public class MemberServiceTest {
	private MemberServiceImpl member_service;

	@Before
	public void setUp() {
		try {
			FillDatabase.fillDatabase();
			member_service = MemberServiceImpl.getInstance();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Test
	public void getAllTest() {
		try {
			List<Member> members = member_service.getAll();

			Assert.assertEquals(6, members.get(0).getId());
			Assert.assertEquals("BALTHAZAR", members.get(0).getLastName());
			Assert.assertEquals(12, members.get(1).getId());
			Assert.assertEquals("CASSANDRE", members.get(1).getLastName());
			Assert.assertEquals(11, members.get(2).getId());
			Assert.assertEquals("CAÏN", members.get(2).getLastName());
			Assert.assertEquals(7, members.get(3).getId());
			Assert.assertEquals("CHASSAGNE", members.get(3).getLastName());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void getListMemberBorrowAvailableTest() {
		try {
			List<Member> members = member_service.getListMemberBorrowAvailable();

			Assert.assertEquals(6, members.get(0).getId());
			Assert.assertEquals("BALTHAZAR", members.get(0).getLastName());
			Assert.assertEquals(12, members.get(1).getId());
			Assert.assertEquals("CASSANDRE", members.get(1).getLastName());
			Assert.assertEquals(11, members.get(2).getId());
			Assert.assertEquals("CAÏN", members.get(2).getLastName());
			Assert.assertEquals(7, members.get(3).getId());
			Assert.assertEquals("CHASSAGNE", members.get(3).getLastName());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void getByIdTest() {
		try {
			Member book = member_service.getById(3);

			Assert.assertEquals(3, book.getId());
			Assert.assertEquals("RENOIR", book.getLastName());
			Assert.assertEquals("Candice", book.getFirstName());
			Assert.assertEquals("26 rue de l'Epeule", book.getAddress());
			Assert.assertEquals("crenoir@mail.com", book.getMail());
			Assert.assertEquals("0485724694", book.getPhone());
			Assert.assertEquals("BASIC", book.getSubscription());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void createTest() {
		try {
			int id = member_service.create("turc", "Etienne", "Palaiseau", "moi@moi", "06");
			Member book = member_service.getById(id);

			Assert.assertEquals(id, book.getId());
			Assert.assertEquals("TURC", book.getLastName());
			Assert.assertEquals("Etienne", book.getFirstName());
			Assert.assertEquals("Palaiseau", book.getAddress());
			Assert.assertEquals("moi@moi", book.getMail());
			Assert.assertEquals("06", book.getPhone());
			Assert.assertEquals("BASIC", book.getSubscription());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void updateTest() {
		try {
			Member book = member_service.getById(3);
			book.setMail("mail@mail");
			member_service.update(book);
			book = member_service.getById(3);

			Assert.assertEquals(3, book.getId());
			Assert.assertEquals("RENOIR", book.getLastName());
			Assert.assertEquals("Candice", book.getFirstName());
			Assert.assertEquals("26 rue de l'Epeule", book.getAddress());
			Assert.assertEquals("mail@mail", book.getMail());
			Assert.assertEquals("0485724694", book.getPhone());
			Assert.assertEquals("BASIC", book.getSubscription());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void deleteTest() {
		try {
			member_service.delete(3);
			member_service.getById(3);
		} catch (ServiceException e) {
			Assert.assertEquals("Not found", e.getMessage());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void countTest() {
		try {
			int c = member_service.count();
			Assert.assertEquals(12, c);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

package com.excilys.librarymanager.test;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

import com.excilys.librarymanager.test.models.BookModelTest;
import com.excilys.librarymanager.test.models.MemberModelTest;
import com.excilys.librarymanager.test.models.BorrowModelTest;

import com.excilys.librarymanager.test.dao.BookDaoTest;
import com.excilys.librarymanager.test.dao.MemberDaoTest;
import com.excilys.librarymanager.test.dao.BorrowDaoTest;

import com.excilys.librarymanager.test.services.BookServiceTest;
import com.excilys.librarymanager.test.services.MemberServiceTest;
import com.excilys.librarymanager.test.services.BorrowServiceTest;

/**
 * main
 */
public class MainTest {

	public static void main(String[] args) {
		JUnitCore runner = new JUnitCore();
		runner.addListener(new TextListener(System.out));

		System.out.println("------------ Model Test ------------");
		System.out.println("Book Model");
		runner.run(BookModelTest.class);
		System.out.println("Member Model");
		runner.run(MemberModelTest.class);
		System.out.println("Borrow Model");
		runner.run(BorrowModelTest.class);

		System.out.println("------------ Dao Test ------------");
		System.out.println("Book Dao");
		runner.run(BookDaoTest.class);
		System.out.println("Member Dao");
		runner.run(MemberDaoTest.class);
		System.out.println("Borrow Dao");
		runner.run(BorrowDaoTest.class);

		System.out.println("------------ Service Test ------------");
		System.out.println("Book Service");
		runner.run(BookServiceTest.class);
		System.out.println("Member Service");
		runner.run(MemberServiceTest.class);
		System.out.println("Borrow Service");
		runner.run(BorrowServiceTest.class);
	}
}

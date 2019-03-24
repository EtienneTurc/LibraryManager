package com.excilys.librarymanager.test;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

import com.excilys.librarymanager.test.models.BookModelTest;
import com.excilys.librarymanager.test.models.MemberModelTest;
import com.excilys.librarymanager.test.models.BorrowModelTest;

import com.excilys.librarymanager.test.dao.BookDaoTest;

/**
 * main
 */
public class MainTest {

	public static void main(String[] args) {
		JUnitCore runner = new JUnitCore();
		runner.addListener(new TextListener(System.out));
		runner.run(BookModelTest.class);
		runner.run(MemberModelTest.class);
		runner.run(BorrowModelTest.class);

		runner.run(BookDaoTest.class);
	}
}

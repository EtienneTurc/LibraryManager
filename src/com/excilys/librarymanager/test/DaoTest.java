package com.excilys.librarymanager.test;

import java.util.List;

import com.excilys.librarymanager.dao.BookDaoImpl;
import com.excilys.librarymanager.dao.BorrowDaoImpl;
import com.excilys.librarymanager.dao.MemberDaoImpl;
import com.excilys.librarymanager.exception.DaoException;

import com.excilys.librarymanager.models.Book;

/**
 * DaoTest
 */
public class DaoTest {
	// public static void main(String[] args) {
	// BookDaoImpl book_dao = BookDaoImpl.getInstance();

	// try {
	// List<Book> books = book_dao.getAll();

	// for (Book b : books) {
	// System.out.print(b.getTitle() + " ");
	// System.out.print(b.getAuthor() + " ");
	// System.out.print(b.getId() + " ");
	// System.out.println(b.getIsbn());
	// }

	// System.out.println("-----------------------------------");

	// Book b = book_dao.getById(5);

	// System.out.print(b.getTitle() + " ");
	// System.out.print(b.getAuthor() + " ");
	// System.out.print(b.getId() + " ");
	// System.out.println(b.getIsbn());

	// // System.out.println("-----------------------------------");

	// // int id = book_dao.create("Mon livre", "Avec mon auteur",
	// "5466-68574-465");

	// // System.out.println(id);

	// // b = book_dao.getById(id);

	// // System.out.print(b.getTitle() + " ");
	// // System.out.print(b.getAuthor() + " ");
	// // System.out.print(b.getId() + " ");
	// // System.out.println(b.getIsbn());

	// System.out.println("-----------------------------------");

	// b.setTitle("Title changed dude");
	// book_dao.update(b);

	// // System.out.println("-----------------------------------");

	// // book_dao.delete(b.getId());

	// } catch (DaoException e) {
	// System.out.println(e);
	// }

	// }

}

package com.excilys.librarymanager.services;

import java.util.List;
import java.util.ArrayList;

import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.exception.ServiceException;

import com.excilys.librarymanager.models.Book;
import com.excilys.librarymanager.services.BookService;
import com.excilys.librarymanager.dao.BookDaoImpl;

import com.excilys.librarymanager.services.BorrowServiceImpl;;

/**
 * BookServiceImpl
 */
public class BookServiceImpl implements BookService {

	private static BookServiceImpl instance;

	private BookServiceImpl() {
	}

	public static BookServiceImpl getInstance() {
		if (instance == null) {
			instance = new BookServiceImpl();
		}
		return instance;
	}

	public List<Book> getAll() throws ServiceException {
		try {
			BookDaoImpl book_dao = BookDaoImpl.getInstance();
			return book_dao.getAll();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<Book> getBooksAvailable() throws ServiceException {
		try {
			List<Book> books_available = new ArrayList<Book>();
			List<Book> books = getAll();
			BorrowServiceImpl borrow_service = BorrowServiceImpl.getInstance();

			for (Book b : books) {
				if (borrow_service.isBookAvailable(b.getId())) {
					books_available.add(b);
				}
			}
			return books_available;
		} catch (ServiceException e) {
			throw e;
		}
	}

	public Book getById(int id) throws ServiceException {
		try {
			BookDaoImpl book_dao = BookDaoImpl.getInstance();
			return book_dao.getById(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public int create(String title, String author, String isbn) throws ServiceException {
		try {
			if (title == "") {
				throw new ServiceException("Empty title");
			}

			BookDaoImpl book_dao = BookDaoImpl.getInstance();
			return book_dao.create(title, author, isbn);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(Book book) throws ServiceException {
		try {
			if (book.getTitle() == "") {
				throw new ServiceException("Empty title");
			}

			BookDaoImpl book_dao = BookDaoImpl.getInstance();
			book_dao.update(book);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void delete(int id) throws ServiceException {
		try {
			BookDaoImpl book_dao = BookDaoImpl.getInstance();
			book_dao.delete(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public int count() throws ServiceException {
		try {
			BookDaoImpl book_dao = BookDaoImpl.getInstance();
			return book_dao.count();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}

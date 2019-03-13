package com.excilys.librarymanager.dao;

import java.util.List;

import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.models.Book;

public interface BookDao {

	public List<Book> getAll() throws DaoException;

	public Book getById(int id) throws DaoException;

	public int create(String title, String author, String isbn) throws DaoException;

	public void update(Book book) throws DaoException;

	public void delete(int id) throws DaoException;

	public int count() throws DaoException;
}

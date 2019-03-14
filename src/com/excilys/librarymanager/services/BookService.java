package com.excilys.librarymanager.services;

import java.util.List;

import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.models.Book;

public interface BookService {

	public List<Book> getAll() throws ServiceException;

	public List<Book> getBooksAvailable() throws ServiceException;

	public Book getById(int id) throws ServiceException;

	public int create(String title, String author, String isbn) throws ServiceException;

	public void update(Book book) throws ServiceException;

	public void delete(int id) throws ServiceException;

	public int count() throws ServiceException;
}

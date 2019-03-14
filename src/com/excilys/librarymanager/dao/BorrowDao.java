package com.excilys.librarymanager.dao;

import java.time.LocalDate;
import java.util.List;

import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.models.Borrow;

public interface BorrowDao {
	public List<Borrow> getAll() throws DaoException;

	public List<Borrow> getListCurrent() throws DaoException;

	public List<Borrow> getListCurrentByMember(int idMember) throws DaoException;

	public List<Borrow> getListCurrentByBook(int idBook) throws DaoException;

	public Borrow getById(int id) throws DaoException;

	public int create(int idMember, int idBook, LocalDate startBorrow) throws DaoException;

	public void update(Borrow Borrow) throws DaoException;

	public int count() throws DaoException;
}

package com.excilys.librarymanager.services;

import java.time.LocalDate;
import java.util.List;

import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.models.Borrow;
import com.excilys.librarymanager.models.Member;

public interface BorrowService {
	public List<Borrow> getAll() throws ServiceException;

	public List<Borrow> getListCurrent() throws ServiceException;

	public List<Borrow> getListCurrentByMember(int idMember) throws ServiceException;

	public List<Borrow> getListCurrentByBook(int idBook) throws ServiceException;

	public Borrow getById(int id) throws ServiceException;

	public void create(int idMember, int idBook, LocalDate startBorrow) throws ServiceException;

	public void returnBook(int id) throws ServiceException;

	public int count() throws ServiceException;

	public boolean isBookAvailable(int idBook) throws ServiceException;

	public boolean isBorrowPossible(Member member) throws ServiceException;
}

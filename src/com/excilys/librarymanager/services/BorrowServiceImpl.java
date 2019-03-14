package com.excilys.librarymanager.services;

import java.util.List;
import java.time.LocalDate;

import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.exception.ServiceException;

import com.excilys.librarymanager.models.Member;
import com.excilys.librarymanager.models.Borrow;

import com.excilys.librarymanager.services.BorrowService;

import com.excilys.librarymanager.dao.BorrowDaoImpl;
import com.excilys.librarymanager.dao.MemberDaoImpl;
import com.excilys.librarymanager.dao.BookDaoImpl;

/**
 * BorrowServiceImpl
 */
public class BorrowServiceImpl implements BorrowService {
	private static BorrowServiceImpl instance;

	private BorrowServiceImpl() {
	}

	public static BorrowServiceImpl getInstance() {
		if (instance == null) {
			instance = new BorrowServiceImpl();
		}
		return instance;
	}

	public List<Borrow> getAll() throws ServiceException {
		try {
			BorrowDaoImpl borrow_dao = BorrowDaoImpl.getInstance();
			return borrow_dao.getAll();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<Borrow> getListCurrent() throws ServiceException {
		try {
			BorrowDaoImpl borrow_dao = BorrowDaoImpl.getInstance();
			return borrow_dao.getListCurrent();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<Borrow> getListCurrentByMember(int idMember) throws ServiceException {
		try {
			BorrowDaoImpl borrow_dao = BorrowDaoImpl.getInstance();
			return borrow_dao.getListCurrentByMember(idMember);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<Borrow> getListCurrentByBook(int idBook) throws ServiceException {
		try {
			BorrowDaoImpl borrow_dao = BorrowDaoImpl.getInstance();
			return borrow_dao.getListCurrentByBook(idBook);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Borrow getById(int id) throws ServiceException {
		try {
			BorrowDaoImpl borrow_dao = BorrowDaoImpl.getInstance();
			return borrow_dao.getById(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void create(int idMember, int idBook, LocalDate startBorrow) throws ServiceException {
		try {
			if ((BookDaoImpl.getInstance().getById(idBook) == null)
					|| (MemberDaoImpl.getInstance().getById(idMember) == null)) {
				throw new ServiceException("The book or the member is not in the database");
			}
			BorrowDaoImpl borrow_dao = BorrowDaoImpl.getInstance();
			borrow_dao.create(idMember, idBook, startBorrow);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void returnBook(int id) throws ServiceException {
		try {
			BorrowDaoImpl borrow_dao = BorrowDaoImpl.getInstance();
			Borrow b = borrow_dao.getById(id);
			if (b == null) {
				throw new ServiceException("The book is not in the database");
			}
			b.setEndBorrow(LocalDate.now());
			borrow_dao.update(b);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public int count() throws ServiceException {
		try {
			BorrowDaoImpl borrow_dao = BorrowDaoImpl.getInstance();
			return borrow_dao.count();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public boolean isBookAvailable(int idBook) throws ServiceException {
		try {
			BorrowDaoImpl borrow_dao = BorrowDaoImpl.getInstance();
			List<Borrow> borrows = borrow_dao.getListCurrentByBook(idBook);
			for (Borrow b : borrows) {
				if (b.getEndBorrow() == null) {
					return false;
				}
			}
			return true;
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public boolean isBorrowPossible(Member member) throws ServiceException {
		try {
			BorrowDaoImpl borrow_dao = BorrowDaoImpl.getInstance();
			List<Borrow> borrows = borrow_dao.getListCurrentByMember(member.getId());
			if (borrows.size() >= member.getSubscriptionValue()) {
				return false;
			}
			return true;
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}

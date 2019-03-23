package com.excilys.librarymanager.services;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.exception.ServiceException;

import com.excilys.librarymanager.models.Member;
import com.excilys.librarymanager.services.MemberService;
import com.excilys.librarymanager.dao.MemberDaoImpl;

/**
 * BookServiceImpl
 */
public class MemberServiceImpl implements MemberService, Serializable {

	private static final long serialVersionUID = -5805806721285509004L;
	private static MemberServiceImpl instance;

	private MemberServiceImpl() {
	}

	public static MemberServiceImpl getInstance() {
		if (instance == null) {
			instance = new MemberServiceImpl();
		}
		return instance;
	}

	public List<Member> getAll() throws ServiceException {
		try {
			MemberDaoImpl member_dao = MemberDaoImpl.getInstance();
			return member_dao.getAll();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<Member> getListMemberBorrowAvailable() throws ServiceException {
		try {
			List<Member> members_available = new ArrayList<Member>();
			List<Member> members = getAll();
			BorrowServiceImpl borrow_service = BorrowServiceImpl.getInstance();

			for (Member m : members) {
				if (borrow_service.isBorrowPossible(m)) {
					members_available.add(m);
				}
			}
			return members_available;
		} catch (ServiceException e) {
			throw e;
		}
	}

	public Member getById(int id) throws ServiceException {
		try {
			MemberDaoImpl member_dao = MemberDaoImpl.getInstance();
			return member_dao.getById(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public int create(String last_name, String first_name, String address, String mail, String phone)
			throws ServiceException {
		try {
			if (last_name == "" || first_name == "") {
				throw new ServiceException("Empty name");
			}
			MemberDaoImpl member_dao = MemberDaoImpl.getInstance();
			return member_dao.create(last_name, first_name, address, mail, phone);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(Member member) throws ServiceException {
		try {
			if (member.getLastName() == "" || member.getFirstName() == "") {
				throw new ServiceException("Empty name");
			}
			MemberDaoImpl member_dao = MemberDaoImpl.getInstance();
			member_dao.update(member);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void delete(int id) throws ServiceException {
		try {
			MemberDaoImpl member_dao = MemberDaoImpl.getInstance();
			member_dao.delete(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public int count() throws ServiceException {
		try {
			MemberDaoImpl member_dao = MemberDaoImpl.getInstance();
			return member_dao.count();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}

package com.excilys.librarymanager.services;

import java.util.List;

import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.models.Member;

public interface MemberService {

	public List<Member> getAll() throws ServiceException;

	public List<Member> getListMemberBorrowAvailable() throws ServiceException;

	public Member getById(int id) throws ServiceException;

	public int create(String last_name, String first_name, String address, String mail, String phone)
			throws ServiceException;

	public void update(Member member) throws ServiceException;

	public void delete(int id) throws ServiceException;

	public int count() throws ServiceException;

}

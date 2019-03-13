package com.excilys.librarymanager.models;

import java.time.LocalDate;

/**
 * Member
 */
public class Borrow {

	private int id;
	private Member member;
	private Book book;
	private LocalDate start_borrow;
	private LocalDate end_borrow;

	public Borrow(int _id, Member m, Book b, LocalDate start, LocalDate end) {
		id = _id;
		member = m;
		book = b;
		start_borrow = start;
		end_borrow = end;
	}

	public int getId() {
		return id;
	}

	public Member getMember() {
		return member;
	}

	public Book getBook() {
		return book;
	}

	public LocalDate getStartBorrow() {
		return start_borrow;
	}

	public LocalDate getEndBorrow() {
		return end_borrow;
	}
}

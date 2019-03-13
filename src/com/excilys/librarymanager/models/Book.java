package com.excilys.librarymanager.models;

/**
 * Member
 */
public class Book {

	private int id;
	private String title;
	private String author;
	private String isbn;

	public Book(int _id, String t, String auth, String i) {
		id = _id;
		title = t;
		author = auth;
		isbn = i;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getIsbn() {
		return isbn;
	}
}

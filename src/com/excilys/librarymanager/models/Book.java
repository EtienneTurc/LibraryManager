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

	public void setId(int _id) {
		id = _id;
	}

	public void setTitle(String _title) {
		title = _title;
	}

	public void setAuthor(String _author) {
		author = _author;
	}

	public void setIsbn(String _isbn) {
		isbn = _isbn;
	}
}

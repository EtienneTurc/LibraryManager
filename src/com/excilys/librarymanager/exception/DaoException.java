package com.excilys.librarymanager.exception;

/**
 * DaoException
 */
public class DaoException extends Exception {

	public DaoException() {
		super("Dao Exception.\n");
	}

	public DaoException(String message) {
		super(message);
	}
}

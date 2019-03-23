package com.excilys.librarymanager.exception;

/**
 * DaoException
 */
public class DaoException extends Exception {

	private static final long serialVersionUID = -5073859305954136754L;

	public DaoException() {
		super("Dao Exception.\n");
	}

	public DaoException(String message) {
		super(message);
	}
}

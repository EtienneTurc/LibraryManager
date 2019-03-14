package com.excilys.librarymanager.exception;

/**
 * DaoException
 */
public class ServiceException extends Exception {

	public ServiceException() {
		super("Service Exception.\n");
	}

	public ServiceException(String message) {
		super(message);
	}
}

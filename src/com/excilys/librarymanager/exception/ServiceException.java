package com.excilys.librarymanager.exception;

/**
 * DaoException
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = -5613882683616401293L;

	public ServiceException() {
		super("Service Exception.\n");
	}

	public ServiceException(String message) {
		super(message);
	}
}

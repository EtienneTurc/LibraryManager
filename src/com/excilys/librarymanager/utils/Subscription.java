package com.excilys.librarymanager.utils;

/**
 * Subscription
 */
public enum Subscription {
	BASIC(2), PREMIUM(5), VIP(10);

	private Subscription(int _value) {
		value = _value;
	}

	private int value;

	public int value() {
		return this.value;
	}
}

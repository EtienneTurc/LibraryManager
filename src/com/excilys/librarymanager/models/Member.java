package com.excilys.librarymanager.models;

import com.excilys.librarymanager.utils.Subscription;;

/**
 * Member
 */
public class Member {

	private int id;
	private String last_name;
	private String first_name;
	private String address;
	private String mail;
	private String phone;
	private Subscription subscription;

	public Member(int _id, String l_name, String f_name, String addr, String email, String phone_number, String subs) {
		id = _id;
		last_name = l_name;
		first_name = f_name;
		address = addr;
		mail = email;
		phone = phone_number;
		subscription = Subscription.valueOf(subs);
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return first_name;
	}

	public String getLastName() {
		return last_name;
	}

	public String getAdress() {
		return address;
	}

	public String getMail() {
		return mail;
	}

	public String getPhone() {
		return phone;
	}

	public String getSubscirption() {
		return subscription.name();
	}
}

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

	public String getAddress() {
		return address;
	}

	public String getMail() {
		return mail;
	}

	public String getPhone() {
		return phone;
	}

	public String getSubscription() {
		return subscription.name();
	}

	public void setId(int _id) {
		id = _id;
	}

	public void setFirstName(String _first_name) {
		first_name = _first_name;
	}

	public void setLastName(String _last_name) {
		last_name = _last_name;
	}

	public void setAddress(String _address) {
		address = _address;
	}

	public void setMail(String _mail) {
		mail = _mail;
	}

	public void setPhone(String _phone) {
		phone = _phone;
	}

	public void setSubscription(String _subscription) {
		subscription = Subscription.valueOf(_subscription);
	}
}

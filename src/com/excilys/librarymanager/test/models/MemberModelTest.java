package com.excilys.librarymanager.test.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.excilys.librarymanager.models.Member;

/**
 * MemberModelTest
 */
public class MemberModelTest {
	private Member member;

	@Before
	public void setUp() {
		member = new Member(1, "TURC", "Etienne", "Palaiseau", "moi@moi", "06", "BASIC");
	}

	@Test
	public void getIdTest() {
		Assert.assertEquals(1, member.getId());
	}

	@Test
	public void getLastNameTest() {
		Assert.assertEquals("TURC", member.getLastName());
	}

	@Test
	public void getFirstNameTest() {
		Assert.assertEquals("Etienne", member.getFirstName());
	}

	@Test
	public void getAddressTest() {
		Assert.assertEquals("Palaiseau", member.getAddress());
	}

	@Test
	public void getPhoneTest() {
		Assert.assertEquals("06", member.getPhone());
	}

	@Test
	public void getMailTest() {
		Assert.assertEquals("moi@moi", member.getMail());
	}

	@Test
	public void getSubscriptionTest() {
		Assert.assertEquals("BASIC", member.getSubscription());
	}

	@Test
	public void setLastNameTest() {
		member.setLastName("Other title");
		Assert.assertEquals("Other title", member.getLastName());
	}

	@Test
	public void setFirstNameTest() {
		member.setFirstName("Other author");
		Assert.assertEquals("Other author", member.getFirstName());
	}

	@Test
	public void setAddressTest() {
		member.setAddress("4224");
		Assert.assertEquals("4224", member.getAddress());
	}

	@Test
	public void setMailTest() {
		member.setMail("4224");
		Assert.assertEquals("4224", member.getMail());
	}

	@Test
	public void setPhoneTest() {
		member.setPhone("4224");
		Assert.assertEquals("4224", member.getPhone());
	}

	@Test
	public void setSubscriptionTest() {
		member.setSubscription("VIP");
		Assert.assertEquals("VIP", member.getSubscription());
	}
}

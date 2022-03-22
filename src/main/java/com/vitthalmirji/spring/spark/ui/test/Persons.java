package com.vitthalmirji.spring.spark.ui.test;

import java.io.Serializable;

public class Persons implements Serializable {
	String personId;
	String firstName;
	String lastName;
	String email;
	String age;
	public Persons() {
		super();
	}
	public Persons(String personId, String firstName, String lastName, String email, String age) {
		super();
		this.personId = personId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Persons [personId=" + personId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", age=" + age + "]";
	}
	
}

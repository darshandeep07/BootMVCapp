package com.cg.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Employee {

	@Id
	@NotNull
	@GeneratedValue
	private long id;
	private String name;
	private int phonenumber;
	
	public Employee() {
	}
	
	public Employee(long id) {
		this.id = id;
	}

	
	public Employee(int phonenumber, String name) {
		this.phonenumber = phonenumber;
		this.name = name;
	}


	public int getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(int phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}

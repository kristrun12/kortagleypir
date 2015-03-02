package com.kla.cardservice.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
		
	//socialsecurity number
	private String ssn;
	private String firstName;
	private String lastName;
	//device id
	private String dev_id;
	private int id;
	
	public String getDev_id() {
		return dev_id;
	}

	public void setDev_id(String dev_id) {
		this.dev_id = dev_id;
	}

	public String getLastName() {
		
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}




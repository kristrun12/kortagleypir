package com.kla.cardservice.data;

public class Token {
	
	String token;
	String date;
	String usr_id;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	String device_id;
	
	public String getUsr_id() {
		return usr_id;
	}
	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}

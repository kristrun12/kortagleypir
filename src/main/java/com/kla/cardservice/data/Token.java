package com.kla.cardservice.data;

import java.util.Date;

public class Token {
	
	private int token_id;
	private String tokenitem;
	private Date date;
	private int usr_id;
	private int card_id;
	private boolean used;
	private String device_id;
	
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public int getCard_id() {
		return card_id;
	}
	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
		
	public int getUsr_id() {
		return usr_id;
	}
	public void setUsr_id(int usr_id) {
		this.usr_id = usr_id;
	}
	
	public String getTokenitem() {
		return tokenitem;
	}
	public void setTokenitem(String tokenitem) {
		this.tokenitem = tokenitem;
	}
	public int getToken_id() {
		return token_id;
	}
	public void setToken_id(int token_id) {
		this.token_id = token_id;
	}
	public boolean isUsed() {
		return used;
	}
	public void setUsed(boolean used) {
		this.used = used;
	}
	
}

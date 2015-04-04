package com.kla.cardservice.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Card 
{
	private int card_id;
	
	private int usr_id;
	
	private String cardholder;

	private String cardnumber;
	
	private String validity;
	
	private String cvv;
	
	private String device_id;
	
	private String pin;
	
	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getCardholder() {
		return cardholder;
	}

	public void setCardholder(String cardholder) {
		this.cardholder = cardholder;
	}

	public String getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}

	public int getUsr_id() {
		return usr_id;
	}

	public void setUsr_id(int usr_id) {
		this.usr_id = usr_id;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

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
	
}

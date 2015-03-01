package com.kla.cardservice.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Card 
{
	private int id;
	
	private String cardHolderName;

	private String cardNumber;
	
	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}

package com.kla.cardservice.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Card 
{
	private int id;
	
	private String cardholdername;

	private String cardnumber;
	
	private String expdate;
	
	private int cmv;
	
	private int devid;
	
	public String getCardholdername() {
		return cardholdername;
	}

	public void setCardholdername(String cardholdername) {
		this.cardholdername = cardholdername;
	}

	public String getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExpdate() {
		return expdate;
	}

	public void setExpdate(String expdate) {
		this.expdate = expdate;
	}

	public int getCmv() {
		return cmv;
	}

	public void setCmv(int cmv) {
		this.cmv = cmv;
	}

	public int getDevid() {
		return devid;
	}

	public void setDevid(int devid) {
		this.devid = devid;
	}
	
}

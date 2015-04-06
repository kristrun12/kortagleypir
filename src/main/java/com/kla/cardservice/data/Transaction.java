package com.kla.cardservice.data;

import java.util.Date;

public class Transaction {
	
		private int transaction_id;
	 	public int getTransaction_id() {
			return transaction_id;
		}
		public void setTransaction_id(int transaction_id) {
			this.transaction_id = transaction_id;
		}
		private Date date;
	    private int price;
	    private String tokenitem;
	    private String device_id;
	    private String appPin;
	    private String posPin;
	    private int total;
	    private int card_id;
	    public int getCard_id() {
			return card_id;
		}
		public void setCard_id(int card_id) {
			this.card_id = card_id;
		}
		private String vendor;
	    private int token_id;
	    
		public int getToken_id() {
			return token_id;
		}
		public void setToken_id(int token_id) {
			this.token_id = token_id;
		}
		public String getVendor() {
			return vendor;
		}
		public void setVendor(String vendor) {
			this.vendor = vendor;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public String getTokenitem() {
			return tokenitem;
		}
		public void setTokenitem(String tokenitem) {
			this.tokenitem = tokenitem;
		}
		public String getDevice_id() {
			return device_id;
		}
		public void setDevice_id(String device_id) {
			this.device_id = device_id;
		}
		public String getAppPin() {
			return appPin;
		}
		public void setAppPin(String appPin) {
			this.appPin = appPin;
		}
		public String getPosPin() {
			return posPin;
		}
		public void setPosPin(String posPin) {
			this.posPin = posPin;
		}
		public int getTotal() {
			return total;
		}
		public void setTotal(int total) {
			this.total = total;
		}
}

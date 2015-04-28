package com.kla.cardservice;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import com.kla.cardservice.dao.CardDAO;
import com.kla.cardservice.dao.TokenDAO;
import com.kla.cardservice.dao.TransactionDAO;
import com.kla.cardservice.data.Card;
import com.kla.cardservice.data.Token;
import com.kla.cardservice.data.Transaction;
import com.kla.cardservice.exceptions.TransactionException;

@Path("/transaction")
public class TransactionResource {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHi()
	{
		return "Hi transaction!";
	}
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Transaction> getAllTransaction()
	{
		return new TransactionDAO().getAllTransactions();
		
	}
	@GET()
	@Path("/all/{card_id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public List<Transaction> getTransactionsByCardID(@PathParam("card_id")int card_id)
	{
		try{
			System.out.println("Finding "+card_id);
			return new TransactionDAO().getTransactionsByCardID(card_id);
		
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	@POST()
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Transaction createTransaction(Transaction trans)
	{
		//setting the token to string to send to function
		String s = trans.getTokenitem();
		
		//checking if token is valid
		Token t = new TokenDAO().getTokenByID(s);
		
		if(t == null || t.isUsed())
		{
			throw new TransactionException("Ógilt kort, færslu hafnað", Status.BAD_REQUEST);
		}
		
		//
		// Check transaction
		if(trans.getPrice()< 1)
		{
			throw new TransactionException("Verðið verður að vera hærra en núll", Status.BAD_REQUEST);
		}
		
		//check Card balance
		Card c = new CardDAO().getCardByID(t.getCard_id());
		if(c.getBalance() - trans.getPrice() < 0)
		{
			throw new TransactionException("Ekki næg innistæða", Status.BAD_REQUEST);
		}
		
		//ok go ahead with the transaction
		
		trans.setDate(new Date());
		trans.setToken_id(t.getToken_id());
		trans.setCard_id(t.getCard_id());
		//set the balance
		new CardDAO().changeBalance(-trans.getPrice(),c.getCard_id());
		new TokenDAO().setTokenAsUsed(t);
		int transId = new TransactionDAO().registerTransaction(trans);
		trans.setTransaction_id(transId);
		
		//return transaction id 
		return trans;
	}
}

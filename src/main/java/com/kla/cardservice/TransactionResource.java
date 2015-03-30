package com.kla.cardservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kla.cardservice.dao.CardDAO;
import com.kla.cardservice.dao.TransactionDAO;
import com.kla.cardservice.data.Card;
import com.kla.cardservice.data.Transaction;

@Path("/transaction")
public class TransactionResource {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHi()
	{
		return "Hi Transaction!";
	}
	
	
	@POST()
	@Consumes(MediaType.APPLICATION_JSON)
	public void createTransaction(Transaction newTransaction)
	{
		new TransactionDAO().createTransaction(newTransaction);
	}

}

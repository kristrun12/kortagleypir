package com.kla.cardservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kla.cardservice.dao.CardDAO;
import com.kla.cardservice.dao.TransactionDAO;
import com.kla.cardservice.dao.UserDAO;
import com.kla.cardservice.data.Transaction;
import com.kla.cardservice.data.User;

@Path("/transaction")
public class TransactionResource {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHi()
	{
		return "Hi transaction!";
	}

	@GET
	@Path("/allmain")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Transaction> getAllMainCardTransaction()
	{
		return new TransactionDAO().getAllMainTransactions();
		
	}
	@GET
	@Path("/allextra")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Transaction> getAllExtraCardTransaction()
	{
		return new TransactionDAO().getAllExtraTransactions();
		
	}
	@GET
	@Path("/allspare")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Transaction> getAllSpareCardTransaction()
	{
		return new TransactionDAO().getAllSpareTransactions();
		
	}
	
	@POST()
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Transaction insertTransaction(Transaction newTransaction)
	{
		if(newTransaction.getCardname().equals("mainCard"))
		{
			new TransactionDAO().registerMainTransaction(newTransaction);	
		}
		else if(newTransaction.getCardname().equals("extraCard"))
		{
			new TransactionDAO().registerExtraTransaction(newTransaction);
		}
		else if(newTransaction.getCardname().equals("spareCard"))
		{
			new TransactionDAO().registerSpareTransaction(newTransaction);
		}
		return newTransaction;
	}
}

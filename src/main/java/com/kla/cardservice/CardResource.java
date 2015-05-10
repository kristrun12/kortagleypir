package com.kla.cardservice;

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
import com.kla.cardservice.dao.TransactionDAO;
import com.kla.cardservice.dao.UserDAO;
import com.kla.cardservice.data.Card;
import com.kla.cardservice.data.Transaction;
import com.kla.cardservice.data.User;
import com.kla.cardservice.exceptions.CardException;
import com.kla.cardservice.exceptions.UserException;


@Path("/card")
public class CardResource {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHi()
	{

		return "Hi Card!";
	}
	
		
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public List<Card> getAllCards()
	{
		return new CardDAO().getAllCards();
			
	}
	
	@GET()
	@Path("/balance/{card_id}")
	@Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
	public Integer getBalanceByCardID(@PathParam("card_id")int card_id)
	{
		try{
			System.out.println("Finding balance "+card_id);
			
			//get the information about the card
			
			Card c = new CardDAO().getCardByID(card_id);
			//return balance to user
			 
			return c.getBalance();
			//return new CardDAO().getBalanceByCardID(card_id);
		
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	@POST()
	@Consumes(MediaType.APPLICATION_JSON )
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Card registerCard(Card newCard)
	{
			String n = "4444444444444444";
		if(newCard.getCardnumber().equals(n))
		{
			throw new CardException("Ekki gilt kortanúmer", Status.BAD_REQUEST);
		}
		int card_id = new CardDAO().registerCard(newCard);
	
		newCard.setCard_id(card_id);
		return newCard;
	}
	
}

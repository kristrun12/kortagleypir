package com.kla.cardservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kla.cardservice.dao.CardDAO;
import com.kla.cardservice.dao.UserDAO;
import com.kla.cardservice.data.Card;
import com.kla.cardservice.data.User;


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
		@Produces(MediaType.APPLICATION_JSON)
		public List<Card> getAllCards()
		{
			return new CardDAO().getAllCards();
			
		}
		
		
	

	@POST()
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Card registerCard(Card newCard)
	{
	
		int card_id = new CardDAO().registerCard(newCard);
	
		newCard.setCard_id(card_id);
		return newCard;
	}

	
	
}

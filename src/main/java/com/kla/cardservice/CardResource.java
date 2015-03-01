package com.kla.cardservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kla.cardservice.data.Card;

@Path("/card")
public class CardResource {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHi()
	{

		//createDatabase();

		return "Hi Token!";
	}

	@GET()
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Card getCard(@PathParam("id") final String id)
	{
		Card card = new Card();
		card.setCardHolderName("Beggi Begg");
		card.setCardNumber(id);
		return card;
	}
	
	@POST()
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createCard(Card newCard)
	{
		//int cardId = new CardDAO().saveCard(newCard);
		//System.out.println(cardId);
		System.out.println(newCard.getCardHolderName());
		return newCard.getCardNumber();
	}
	
	
}

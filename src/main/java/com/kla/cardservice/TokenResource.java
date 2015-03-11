package com.kla.cardservice;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kla.cardservice.dao.CardDAO;
import com.kla.cardservice.dao.TokenDAO;
import com.kla.cardservice.data.Card;
import com.kla.cardservice.data.Token;

@Path("/token")
public class TokenResource{
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHi()
	{
		return "Hi Token!";
	}
	
	
	@POST()
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Token getToken(Token newToken)
	{
		newToken.setTokenone(UUID.randomUUID().toString());
		newToken.setTokentwo(UUID.randomUUID().toString());
		newToken.setTokenthree(UUID.randomUUID().toString());
		new TokenDAO().registerToken(newToken);
		return newToken;
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Token> getAllTokens()
	{
		List <Token> t = new TokenDAO().getAllTokens();
		System.out.println(t);
		return new TokenDAO().getAllTokens();
		
	}
	//@GET
		//@Produces(MediaType.APPLICATION_JSON)
		//@Path("/getlist")
		/*public List<String> getTokens()
		{
			List<String> tokens = new ArrayList<String>();
			tokens.add(UUID.randomUUID().toString());
			tokens.add(UUID.randomUUID().toString());
			tokens.add(UUID.randomUUID().toString());
		
			return tokens;
		}*/
}

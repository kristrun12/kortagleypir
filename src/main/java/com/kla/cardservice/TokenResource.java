package com.kla.cardservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.kla.cardservice.dao.UserDAO;
import com.kla.cardservice.data.Card;
import com.kla.cardservice.data.Token;
import com.kla.cardservice.data.User;

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
		//create the token
		newToken.setToken(UUID.randomUUID().toString());
		//set the date
		
		String date = new SimpleDateFormat("HH:mm:ss.ssss").format(new Date());
		newToken.setDate(date);
		new TokenDAO().registerToken(newToken);
		return newToken;
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Token> getAllTokens()
	{
		
		return new TokenDAO().getAllTokens();
		
	}
	
}

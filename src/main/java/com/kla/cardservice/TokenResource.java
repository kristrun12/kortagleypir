package com.kla.cardservice;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kla.cardservice.dao.TokenDAO;
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
		
		newToken.setToken(UUID.randomUUID().toString());
		//java.util.Date date = new java.util.Date();
		//long time = date.getTime();
		//String s = String.valueOf(date);
		//newToken.setDate(s);
		//DateFormat df = new SimpleDateFormat("yyyyMMdd  HH:mm");
		//String sdt = df.format(new Date(System.currentTimeMillis()));
		//newToken.setDate(sdt);
		long now = System.currentTimeMillis();

		java.util.Date date = new java.util.Date(now);
		newToken.setDate(date.toString());
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

package com.kla.cardservice;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

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
	public Token createToken(Token newToken)
	{		
			newToken.setTokenitem(UUID.randomUUID().toString());
			
			newToken.setDate(new Date());
			
			int id = new TokenDAO().registerToken(newToken);
		
		return newToken;
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Token> getAllTokens()
	{
		try{
			return new TokenDAO().getAllTokens();
		}catch(Exception ex)
		{
			throw new WebApplicationException("Failed with "+ex.getMessage(),Status.BAD_REQUEST);
		}
	}
	
	@GET()
	@Path("/{tokenitem}")
	@Produces(MediaType.APPLICATION_JSON)
	public Token getToken(@PathParam("tokenitem")String tokenitem)
	{
		try{
			System.out.println("Finding "+tokenitem);
			return new TokenDAO().getTokenByID(tokenitem);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	
}

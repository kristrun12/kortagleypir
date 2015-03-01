package com.kla.cardservice;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/token")
public class TokenResource extends BaseResource{
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHi()
	{
		return "Hi Card!";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getlist")
	public List<String> getTokens()
	{
		List<String> tokens = new ArrayList<String>();
		tokens.add(UUID.randomUUID().toString());
		tokens.add(UUID.randomUUID().toString());
		tokens.add(UUID.randomUUID().toString());
		tokens.add(UUID.randomUUID().toString());
		tokens.add(UUID.randomUUID().toString());
		tokens.add(UUID.randomUUID().toString());
		tokens.add(UUID.randomUUID().toString());
		return tokens;
	}
	
}

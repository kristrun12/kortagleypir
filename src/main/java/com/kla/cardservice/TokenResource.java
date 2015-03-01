package com.kla.cardservice;

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
	
	
}

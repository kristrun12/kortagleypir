package com.kla.cardservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kla.cardservice.dao.CardDAO;
import com.kla.cardservice.dao.UserDAO;
import com.kla.cardservice.data.User;



@Path("/user")
public class UserResource {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHi()
	{
		return "Hi User!";
	}
	
	
	@POST()
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createUser(User newUser)
	{
		new CardDAO().createDatabase();
		int userId = new UserDAO().addUser(newUser);
		//System.out.println(userId);
		System.out.println(newUser.getFirstName());
		return newUser.getSsn();
	}
}

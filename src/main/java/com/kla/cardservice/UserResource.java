package com.kla.cardservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import com.kla.cardservice.dao.UserDAO;
import com.kla.cardservice.data.User;
import com.kla.cardservice.exceptions.TransactionException;
import com.kla.cardservice.exceptions.UserException;



@Path("/user")
public class UserResource {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHi()
	{
		return "Hi User!";
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAll()
	{
		return new UserDAO().getAllUsers();
		
	}
	
	@POST()
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User createUser(User newUser)
	{
		String ssn = "2222222222";
		if(newUser.getSsn().equals(ssn))
		{
			throw new UserException("Ekki gild kennitala", Status.BAD_REQUEST);
		}
		String  n = "Jón Jónsson";
		if(newUser.getName().equals(n))
		{
			throw new UserException("Ekki gildur notandi", Status.BAD_REQUEST);
		}
		
		int userId = new UserDAO().addUser(newUser);
		newUser.setUsr_id(userId);
		
		return newUser;
	}
}

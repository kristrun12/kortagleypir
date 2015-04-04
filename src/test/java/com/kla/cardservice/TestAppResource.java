package com.kla.cardservice;
import java.util.Date;
import java.util.UUID;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import com.kla.cardservice.data.Card;
import com.kla.cardservice.data.Token;
import com.kla.cardservice.data.User;


public class TestAppResource extends JerseyTest {
	
	@Override
    protected Application configure() {
    	
    	
        return new ResourceConfig(UserResource.class,CardResource.class,TokenResource.class);
    }
	 
	@Test
	public void testPost() {

		//
		// Create a mock User
		User user = new User();
		user.setUsr_id(0);
		user.setName("Lina Long");
		user.setSsn("12345432");
		user.setDevice_id(UUID.randomUUID().toString());
		
		//
		// Send user to resource
		final Response responseCreateUser = target().path("user").request().post(Entity.entity(user, MediaType.APPLICATION_JSON));
		
		//
		// Parse response to objectd
		final User createdUser = responseCreateUser.readEntity(User.class);

		//
		// Create a mock Card with data from the created user
		Card newCard = new Card();

		newCard.setUsr_id(createdUser.getUsr_id());
		newCard.setCardholder(createdUser.getName());
		newCard.setCardnumber("123123123");
		newCard.setCvv("555");
		newCard.setValidity("15/15");
		newCard.setDevice_id("6");
		newCard.setPin("5289");

		
		//
		// Send card to resource
		final Response responseCreateCard = target().path("card").request()
				.post(Entity.entity(newCard, MediaType.APPLICATION_JSON));

		// Parse response to objectd
				final Card createdCard = responseCreateCard.readEntity(Card.class);
		System.out.println(responseCreateUser.getClass().getName());
		System.out.println(responseCreateUser);

		Token token = new Token();
		token.setTokenitem(UUID.randomUUID().toString());
		token.setDate(new Date());
		token.setUsr_id(createdUser.getUsr_id());
		token.setCard_id(createdCard.getCard_id());
		token.isUsed();

		final Response responseMsgT = target().path("token").request()
				.post(Entity.entity(token, MediaType.APPLICATION_JSON));

		System.out.println(responseCreateUser.getClass().getName());
		System.out.println(responseCreateUser);
	}
	    

}

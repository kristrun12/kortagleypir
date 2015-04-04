package com.kla.cardservice;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.UUID;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.kla.cardservice.dao.CardDAO;
import com.kla.cardservice.dao.UserDAO;
import com.kla.cardservice.data.Card;
import com.kla.cardservice.data.Token;
import com.kla.cardservice.data.User;
public class TokenResourceTest extends JerseyTest{

	 	@Override
	    protected Application configure() {
	    	
	    	
	        return new ResourceConfig(TokenResource.class);
	    }

	    

	    /**
	     * Test to see that the message "Got it!" is sent in the response.
	     */
	    @Test
	    public void testPost() {
	    	
	    	//get a valid user from the database
	    	User u = new UserDAO().getAllUsers().get(0);
	    	//get a valid card from the database
	    	Card c = new CardDAO().getAllCards().get(0);
	    	Token token = new Token();
	    	token.setTokenitem(UUID.randomUUID().toString());
	    	token.setDate(new Date());
	    	token.setUsr_id(u.getUsr_id());
	    	token.setCard_id(c.getCard_id());
	    	token.isUsed();
	    		    	
	    	
	        final Response responseMsg = target().path("token").request().post(Entity.entity(token, MediaType.APPLICATION_JSON));
	        
	        System.out.println(responseMsg.getClass().getName());
	        System.out.println(responseMsg);
	    }
	    
}

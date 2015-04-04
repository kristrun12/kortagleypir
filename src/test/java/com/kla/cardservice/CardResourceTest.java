package com.kla.cardservice;

import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.kla.cardservice.dao.UserDAO;
import com.kla.cardservice.data.Card;
import com.kla.cardservice.data.User;




public class CardResourceTest extends JerseyTest {

	
	
    @Override
    protected Application configure() {
    	
    	
        return new ResourceConfig(CardResource.class);
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
   /* @Test
    public void tesstGetIt() {
        final String responseMsg = target().path("card").path("123").request().get(String.class);

        assertTrue(responseMsg.contains("123"));
    }*/
    
    @Test
    public void testGetCard() {
        final String responseMsg = target().path("card").request().get(String.class);
        assertTrue(responseMsg.contains("Hi"));
    } 

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testPost() {
    	
    	//get a valid user from the database
    	User u = new UserDAO().getAllUsers().get(0);
    	
    	//simulates data comming from the app
    	Card newCard = new Card();

    	newCard.setUsr_id(u.getUsr_id());
    	newCard.setCardholder("Palli sig");
    	newCard.setCardnumber("123123123");
    	newCard.setCvv("555");
    	newCard.setValidity("15/15");
    	newCard.setDevice_id("6");
    	newCard.setPin("5289");
    	
    	
    	//register the newCArd
        final Response responseMsg = target().path("card").request().post(Entity.entity(newCard, MediaType.APPLICATION_JSON));
        
        System.out.println(responseMsg.getClass().getName());
        System.out.println(responseMsg);
    }
    
}

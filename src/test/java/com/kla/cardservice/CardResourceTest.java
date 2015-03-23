package com.kla.cardservice;

import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.kla.cardservice.data.Card;




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
    	Card newCard = new Card();
    	newCard.setUsr_id("7");
    	newCard.setCardholder("Palli sig");
    	newCard.setCardnumber("123123123");
    	newCard.setCvv("555");
    	newCard.setValidity("15/15");
    	newCard.setDevice_id("6");
    	
    	
    	
        final Response responseMsg = target().path("card").request().post(Entity.entity(newCard, MediaType.APPLICATION_JSON));
        
        System.out.println(responseMsg.getClass().getName());
        System.out.println(responseMsg);
    }
    
}

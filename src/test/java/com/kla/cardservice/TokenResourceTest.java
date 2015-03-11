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
import com.kla.cardservice.data.Token;
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
	    	Token token = new Token();
	    	token.setUsr_id("456");
	    	token.setDevice_id("85125");
	    	token.setTokenone("ljh654");
	    	token.setTokentwo("d6");
	    	token.setTokenthree("d5fg4");
	    	
	    	
	        final Response responseMsg = target().path("token").request().post(Entity.entity(token, MediaType.APPLICATION_JSON));
	        
	        System.out.println(responseMsg.getClass().getName());
	        System.out.println(responseMsg);
	    }
	    
}

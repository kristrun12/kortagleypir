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
import com.kla.cardservice.data.Transaction;



public class TransactionResourceTest extends JerseyTest{
	
	@Override
    protected Application configure() {
    	
    	
        return new ResourceConfig(TransactionResource.class);
    }

    

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testMain() {
    	
    	Transaction transaction = new Transaction();
    	
    	transaction.setAppPin("4522");
    	transaction.setPrice(5000);
    	transaction.setTotal(150000);
    	transaction.setVendor("Kostur");
    	transaction.setToken_id(3);
    	
    	
        final Response responseMsg = target().path("transaction").request().post(Entity.entity(transaction, MediaType.APPLICATION_JSON));
        
        System.out.println(responseMsg.getClass().getName());
        System.out.println(responseMsg);
    }
    

}

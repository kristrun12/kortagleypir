package com.kla.cardservice;

import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;


import com.kla.cardservice.data.User;

public class UserResourceTest extends JerseyTest {

	
	
    @Override
    protected Application configure() {
    	
    	
        return new ResourceConfig(UserResource.class);
    }

    
    
    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testUserPost() {
    	User user = new User();
    	user.setFirstName("Bella");
    	user.setLastName("Beenno");
    	user.setSsn("12345432");
    	user.setDev_id("00");
    	
    	
        final Response responseMsg = target().path("user").request().post(Entity.entity(user, MediaType.APPLICATION_JSON));
        
        System.out.println(responseMsg.getClass().getName());
        System.out.println(responseMsg);
    }
    
}

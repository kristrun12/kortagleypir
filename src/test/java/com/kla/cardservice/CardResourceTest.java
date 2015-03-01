package com.kla.cardservice;

import static org.junit.Assert.assertTrue;


import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

public class CardResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(CardResource.class);
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void tesstGetIt() {
        final String responseMsg = target().path("card").path("123").request().get(String.class);

        assertTrue(responseMsg.contains("123"));
    }
}

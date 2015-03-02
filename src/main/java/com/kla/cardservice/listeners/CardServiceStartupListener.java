package com.kla.cardservice.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.kla.cardservice.dao.CardDAO;

/**
 * Application Lifecycle Listener implementation class CardServiceStartupListener
 *
 */
@WebListener
public class CardServiceStartupListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public CardServiceStartupListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	System.out.println("prepareing card service database");
    	new CardDAO().createDatabase();
    }
	
}

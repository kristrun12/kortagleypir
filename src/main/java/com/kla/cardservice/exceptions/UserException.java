package com.kla.cardservice.exceptions;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class UserException extends WebApplicationException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6245874239425366865L;

	public UserException(String message, Status status) {
		super(Response.status(status.getStatusCode()).type(MediaType.TEXT_PLAIN).entity(message).build());
	}

}

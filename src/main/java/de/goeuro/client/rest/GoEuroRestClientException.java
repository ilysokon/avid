package de.goeuro.client.rest;

import de.goeuro.client.GoEuroClientException;

/**
 * The {@code GoEuroRestClientException} class is the superclass of all errors and
 * exceptions during GoEuro REST communication.
 */
@SuppressWarnings("serial")
public class GoEuroRestClientException extends GoEuroClientException{

	public GoEuroRestClientException(String message, Throwable cause) {
		super(message, cause);
	}

}
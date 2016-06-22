package de.goeuro.client;

/**
 * The {@code GoEuroClientException} class is the superclass of all errors and
 * exceptions during GoEuro communication.
 */
@SuppressWarnings("serial")
public class GoEuroClientException extends Exception{

	public GoEuroClientException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public GoEuroClientException(String message) {
		super(message);
	}
}
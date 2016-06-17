package de.goeuro.client;

/**
 * The {@code ReportException} class is the superclass of all errors and
 * exceptions during the report generation.
 */
@SuppressWarnings("serial")
public class ReportException extends Exception{

	public ReportException(String message, Throwable cause) {
		super(message, cause);
	}

}

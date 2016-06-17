package de.goeuro.report;

/**
 * Report Processor
 *
 * @param <DATA> the type of data  to be processed by this report processor
 */
public interface ReportProcessor<DATA> {
	byte[] process(DATA reportData) throws ReportException;
	
}

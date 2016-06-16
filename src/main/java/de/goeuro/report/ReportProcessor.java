package de.goeuro.report;

/**
 * Report Processor
 *
 * @param <DATA> data definition
 */
public interface ReportProcessor<DATA> {
	byte[] process(DATA reportData) throws ReportException;
	
}

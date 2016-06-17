package de.goeuro.report;

import de.goeuro.report.api.DataProvider;
import de.goeuro.report.api.ReportResponse;

/**
 *  Report Engine to process the report with given format, report name and data provider
 *
 */
public interface ReportEngine {
	
	/**
	 * Process report
	 * 
	 * @param format report format
	 * @param reportName define what kind of report should be processed
	 * @param dataProvider report data to be processed
	 * @return the report in the specified format is filled by data from dataProvider
	 * @throws ReportException if something went wrong during report processing
	 */
	<DATA> ReportResponse process(ReportFormat format, ReportName reportName,
			DataProvider<DATA> dataProvider)throws ReportException;
	
}

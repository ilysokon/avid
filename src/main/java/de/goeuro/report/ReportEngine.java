package de.goeuro.report;

import de.goeuro.report.api.DataProvider;
import de.goeuro.report.api.ReportResponse;

/**
 *  Report Engine to process the report with given format, report name and data provider
 *
 */
public interface ReportEngine {
	<DATA> ReportResponse process(ReportFormat format, ReportName reportName,
			DataProvider<DATA> dataProvider)throws ReportException;
	
}

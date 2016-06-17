package de.goeuro.report.api;

import de.goeuro.report.ReportFormat;
import de.goeuro.report.ReportName;

/**
 * Request for report generating 
 *
 * @param <DATA> the type of data the report is based on
 */
public class ReportRequest<DATA> implements DataProvider<DATA>{
	// report name to generate
	private ReportName reportName;
	// report format to generate
	private ReportFormat format;
	// data the report is based on
	private DATA data;
	
	public ReportFormat getFormat() {
		return format;
	}
	public void setFormat(ReportFormat format) {
		this.format = format;
	}
	public ReportName getReportName() {
		return reportName;
	}
	public void setReportName(ReportName reportName) {
		this.reportName = reportName;
	}
	@Override
	public DATA getData() {
		return data;
	}
	public void setData(DATA data) {
		this.data = data;
	}
}

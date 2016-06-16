package de.goeuro.report.service;

import java.util.Collection;

import de.goeuro.api.GeoData;
import de.goeuro.report.ReportException;
import de.goeuro.report.api.DataProvider;
import de.goeuro.report.api.ReportResponse;

/**
 * Define Entry point service for report generation 
 *
 * @param <REQ>
 */
public interface GeoReportService<REQ extends DataProvider<Collection<GeoData>>> {
	ReportResponse handle(REQ request) throws ReportException;
}

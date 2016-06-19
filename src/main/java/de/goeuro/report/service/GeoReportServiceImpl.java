package de.goeuro.report.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.goeuro.model.GeoData;
import de.goeuro.report.ReportEngine;
import de.goeuro.report.ReportException;
import de.goeuro.report.api.ReportRequest;
import de.goeuro.report.api.ReportResponse;

/**
 * GoEuro Report Service Implementation
 *
 */
@Service
public class GeoReportServiceImpl 
implements GeoReportService<ReportRequest<Collection<GeoData>>>{
	
	@Autowired
	private ReportEngine reportEngine;

	@Override
	public ReportResponse handle(ReportRequest<Collection<GeoData>> request) throws ReportException {
		return reportEngine.process(
				request.getFormat(), request.getReportName(), 
				request);
	}
}

package de.goeuro.report.service;

import java.util.Collection;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Service;

import de.goeuro.api.GeoData;
import de.goeuro.report.ReportEngine;
import de.goeuro.report.ReportException;
import de.goeuro.report.api.ReportRequest;
import de.goeuro.report.api.ReportResponse;

/**
 * Statistics Report Service Implementation
 *
 */
@Service
public class GeoReportServiceImpl 
implements GeoReportService<ReportRequest<Collection<GeoData>>>, BeanFactoryAware{
	
	private BeanFactory beanFactory;

	@Override
	public ReportResponse handle(ReportRequest<Collection<GeoData>> request) throws ReportException {
		ReportEngine reportEngine = (ReportEngine)beanFactory.getBean("reportEngine");
		return reportEngine.process(
				request.getFormat(), request.getReportName(), 
				request);
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
		
	}
}

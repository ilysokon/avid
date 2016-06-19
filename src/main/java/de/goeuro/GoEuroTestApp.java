package de.goeuro;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import de.goeuro.client.GoEuroClient;
import de.goeuro.client.GoEuroClientException;
import de.goeuro.client.GoEuroReportRequest;
import de.goeuro.model.GeoData;
import de.goeuro.report.ReportException;
import de.goeuro.report.api.ReportFormat;
import de.goeuro.report.api.ReportName;
import de.goeuro.report.api.ReportRequest;
import de.goeuro.report.api.ReportResponse;
import de.goeuro.report.service.GeoReportService;

/*
 * GoEuroTest Standalone Application
 * 
 */

@Component
public class GoEuroTestApp {
	private static Logger logger = Logger.getLogger(GoEuroTestApp.class);
	
	/** csv file name for generating goeuro report */
	private static String CSV_FILE = "goeuro.csv";
	
	@Autowired
	private GeoReportService<ReportRequest<Collection<GeoData>>> reportService;
	
	@Autowired
	private GoEuroClient goEuroClient;
	
	public void start(final String cityName) {
		try {
			logger.info("send request for geo data");
			Collection<GeoData> geoData = goEuroClient.getGeoData(cityName);
			logger.info("geo data is received: " + geoData);
			
			GoEuroReportRequest reportRequest = new GoEuroReportRequest();
			reportRequest.setData(geoData);
			reportRequest.setFormat(ReportFormat.CSV);
			reportRequest.setReportName(ReportName.GOEURO);
		
			logger.info("goeuro report generating .... ");
			ReportResponse response = reportService.handle(reportRequest);
			FileOutputStream fos = new FileOutputStream (new File(CSV_FILE)); 
		    response.writeTo(fos);
		    logger.info("goeuro report is generated");
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (ReportException e) {
			logger.error(e.getMessage(), e);
		} catch (GoEuroClientException e) {
			logger.error(e.getMessage(), e);
		} 
	}
	
	public static void main(String... args) {
		logger.info("goeuro test is started");
		
		logger.info("goeuro application context preparing...");
		@SuppressWarnings("resource")
		// create main client application context
		ApplicationContext context = 
	            new ClassPathXmlApplicationContext(
	            		new String[]{"classpath:context-client.xml", 
	            				     "classpath:context-report.xml"} );
		
		logger.info("goeuro application context is prepared");
		
		// get goeuro client
		GoEuroTestApp goEuroTestApp = context.getBean(GoEuroTestApp.class);
		
		// check is the city name specified
		if(args.length > 0){
			// start test application
			goEuroTestApp.start(args[0]);
		}
		
		logger.info("goeuro test finished");
    }
}


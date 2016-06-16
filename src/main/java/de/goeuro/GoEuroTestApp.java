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

import de.goeuro.api.GeoData;
import de.goeuro.client.GoEuroRestClient;
import de.goeuro.client.GoEuroReportRequest;
import de.goeuro.report.ReportException;
import de.goeuro.report.ReportFormat;
import de.goeuro.report.ReportName;
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
	
	/** default statistics server url */
	private static String DEFAULT_GOEURO_URL = "http://api.goeuro.com/api/v2/position/suggest/en/";
	
	/** csv file name for generating goeuro report */
	private static String CSV_FILE = "goeuro.csv";
	
	@Autowired
	private GeoReportService<ReportRequest<Collection<GeoData>>> reportService;
	
	@Autowired
	private GoEuroRestClient goEuroRestClient;
	
	public void start(final String cityName) {
		Collection<GeoData> geoData = goEuroRestClient.getGeoData(cityName);
			
		GoEuroReportRequest reportRequest = new GoEuroReportRequest();
		reportRequest.setData(geoData);
		reportRequest.setFormat(ReportFormat.CSV);
		reportRequest.setReportName(ReportName.GOEURO);
		try {
			logger.info("goeuro report generating .... ");
			ReportResponse response = reportService.handle(reportRequest);
			FileOutputStream fos = new FileOutputStream (new File(CSV_FILE)); 
		    response.writeTo(fos);
		    logger.info("goeuro report is generated");
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (ReportException e) {
			logger.error(e.getMessage(), e);
		} 
	}
	
	public static void main(String... args) {
		logger.info(args);
		
		@SuppressWarnings("resource")
		// create main client application context
		ApplicationContext context = 
	            new ClassPathXmlApplicationContext(
	            		new String[]{"classpath:context-client.xml", 
	            				     "classpath:context-report.xml"} );
		// get goeuro client
		GoEuroTestApp client = context.getBean(GoEuroTestApp.class);
		
		// set city name if specified
		if(args.length > 0){
			// start client
			client.start(args[0]);
		}
		
		
    }
}


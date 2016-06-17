package de.goeuro.report;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import de.goeuro.report.api.DataProvider;
import de.goeuro.report.api.ReportFormat;
import de.goeuro.report.api.ReportName;
import de.goeuro.report.api.ReportResponse;

@SuppressWarnings("serial")
@Service("reportEngine")
public class ReportEngineImpl implements ReportEngine, Serializable{
	private static Logger logger = Logger.getLogger(ReportEngineImpl.class);
	
	@SuppressWarnings("rawtypes")
	@Resource(name="reportProcessors")
	private Map<ReportName, Map<ReportFormat, ReportProcessor>> processors;
	
	public ReportEngineImpl(){
	}
		
	@Override
	public <DATA> ReportResponse process(ReportFormat format,
			ReportName reportName, DataProvider<DATA> dataProvider) throws ReportException {
		ReportResponse response = new ReportResponse();
		
		DATA reportData = dataProvider.getData();
		// process the report
		@SuppressWarnings("unchecked")
		byte[] result = processors.get(reportName).get(format).process(reportData);

		try {
			response.write(result);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new ReportException(e.getMessage(), e);
		}
		
		return response;
	}
}

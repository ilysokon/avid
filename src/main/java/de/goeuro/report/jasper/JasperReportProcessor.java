package de.goeuro.report.jasper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import de.goeuro.report.ReportException;
import de.goeuro.report.ReportProcessor;

/**
 * Base Jasper Report Processor 
 *
 * @param <T>
 */
public abstract class JasperReportProcessor<T> 
   implements ReportProcessor<Collection<T>> {
	
	private static Logger logger = Logger.getLogger(JasperReportProcessor.class);
	
	@SuppressWarnings("rawtypes")
	private JRAbstractExporter exporter;
	
	private String template;
	
	@SuppressWarnings("unchecked")
	public byte[] process(Collection<T> data) throws ReportException {
		JasperReport jasperReport;
	    JasperPrint jasperPrint;
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    
	    try{
	      // get report template the report is based on
	      InputStream is = JasperReportProcessor.class.getResourceAsStream(template);
	      // compiling report designs into the ready-to-fill form
	      jasperReport = JasperCompileManager.compileReport(is);
	      // create data source that wraps a collection of JavaBean objects.
          JRBeanCollectionDataSource beanColDataSource =
          new JRBeanCollectionDataSource(data);
          
          Map<String, Object> parameters = new HashMap<String, Object>();
          
          // filling compiled report designs with data from report data sources
	      jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
	      
	      // create an {@link ExporterInput} object with a single item wrapping the {@link JasperPrint} object that will be exported. 
	 	  ExporterInput exporterInput = new SimpleExporterInput(jasperPrint);
	      exporter.setExporterInput(exporterInput);
	      
	      // create a {@link WriterExporterOutput} instance that puts the result into provided <tt>java.io.OutputStream</tt> object. 
	  	  SimpleWriterExporterOutput exporterOutput = new SimpleWriterExporterOutput(outputStream);
	      
	      exporter.setExporterOutput(exporterOutput);
	      
	      exporter.exportReport();
	      
          return outputStream.toByteArray();

	    } catch (JRException e){
	    	logger.error( e.getMessage(), e);
		    throw new ReportException(e.getMessage(), e);
	    } finally{
			try {
				if(outputStream != null){
					outputStream.close();
				}
			} catch (IOException e) {
				logger.error( e.getMessage(), e);
			    throw new ReportException(e.getMessage(), e);
			}
		}
	}
	
	public void setExporter(@SuppressWarnings("rawtypes") JRAbstractExporter exporter) {
		this.exporter = exporter;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

}

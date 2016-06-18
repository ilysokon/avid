package de.goeuro.report.jasper;

import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.export.CsvExporterConfiguration;

/**
 * Exports a JasperReports document to CSV format. It has character output type and exports the document to a
 * grid-based layout.
 *
 */
class JasperJRCsvExporter extends JRCsvExporter {
	
    public void setExporterConfiguration(CsvExporterConfiguration exporterConfiguration){
    	setConfiguration(exporterConfiguration);
    }
}

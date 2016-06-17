package de.goeuro.report.api;

/**
 * Main Report Formats
 * 
 * Define report format with specified content type 
 *
 */
public enum ReportFormat {
   CSV("csv", "application/csv");
   
   final String format;
   final String contentType;
   
   ReportFormat(String format, String contentType){
	  this.format = format; 
	  this.contentType = contentType;
   }
   
   String getFormat(){
	  return format;
   }
   
   public String getContentType() {
	  return contentType;
   }

   @Override
   public String toString(){
	  return format; 
   }
   
   public static ReportFormat toReportFormat(String format){
	  for(ReportFormat reportFormat : values()){
		  if(reportFormat.format.equals(format.toLowerCase())){
			  return reportFormat;
		  }
	  }
	   
	  // return default format
	  return CSV; 
   }
}

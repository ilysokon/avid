package de.goeuro.report;

/*
 * Define all report names
 * 
 */
public enum ReportName {
   GOEURO("goeuro");
   
   final String name;
   
   ReportName(String name){
	  this.name = name; 
   }
   
   String getName(){
	   return name;
   }
   
   @Override
   public String toString(){
	  return name; 
   }
   
   public static ReportName toReportName(String name){
	   for(ReportName reportName : values()){
		   if(reportName.name.equals(name.toLowerCase())){
			   return reportName;
		   }
	   }
	   
	  throw new IllegalArgumentException(); 
   }
}

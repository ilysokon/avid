General description
----------------------
IDE
Eclipse

Project Structure
1. GoEuro Test Application
de.goeuro

2. GoEuro Model 
de.goeuro.model

2. GoEuro Client 
de.goeuro.client - GoEuro Base Client interfaces/classes 

3. GoEuro Client REST Implementation
de.goeuro.client.rest

4. Report Subsystem
Report Subsystem is consist of Report Engine, Jasper Report Implementation and Report Service
Main entry point is GoEuroReportService
For the report generating the ReportRequest should be properly formed with Report Name, Report Format and DATA to be specified.  

4.1. Report Engine
de.goeuro.report - Report Engine Base classes
de.goeuro.report.api - Report Engine API classes 

4.2. Jasper Report Implementation
de.goeuro.report.jasper

4.3. Report Service
de.goeuro.report.service

5. Project Resources:
reports/goeuro.jrxml - Jasper Report Template
app.properties - Application property file
context-client - Spring client context
context-report - Spring report context
log4j.properties - Log4j properties file

Build Project details:
project management is based on Maven


How to start
-------------
Prerequisites:
1. Java 1.8 is properly installed under the system
2. Maven (version 3.x is recommended) is properly installed under the system 


All actions should be done from the root directory:

1. install all modules to repository 
mvn install

sucessfull result should be like this:
...
[INFO] --- maven-install-plugin:2.4:install (default-install) @ dev-test ---
[INFO] Installing /Users/ilysokon/git/goeuro/target/GoEuroTest.jar to /Users/ilysokon/.m2/repository/de/goeuro/dev-test/0.0.1-SNAPSHOT/dev-test-0.0.1-SNAPSHOT.jar
[INFO] Installing /Users/ilysokon/git/goeuro/dependency-reduced-pom.xml to /Users/ilysokon/.m2/repository/de/goeuro/dev-test/0.0.1-SNAPSHOT/dev-test-0.0.1-SNAPSHOT.pom
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 7.924 s
[INFO] Finished at: 2016-06-19T10:41:36+01:00
[INFO] Final Memory: 20M/288M
[INFO] ------------------------------------------------------------------------

2. start GoEuroTest
java -jar GoEuroTest.jar "CITY_NAME"

successfull result should be like this:

bash-3.2$ java -jar GoEuroTest.jar "Berlin"
2016-06-19 10:45:57 INFO  GoEuroTestApp:69 - goeuro test is started
2016-06-19 10:45:57 INFO  GoEuroTestApp:71 - goeuro application context preparing...
2016-06-19 10:45:57 INFO  ClassPathXmlApplicationContext:510 - Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@b1a58a3: startup date [Sun Jun 19 10:45:57 CEST 2016]; root of context hierarchy
2016-06-19 10:45:57 INFO  XmlBeanDefinitionReader:317 - Loading XML bean definitions from class path resource [context-client.xml]
2016-06-19 10:45:57 INFO  XmlBeanDefinitionReader:317 - Loading XML bean definitions from class path resource [context-report.xml]
2016-06-19 10:45:58 INFO  PropertiesFactoryBean:172 - Loading properties file from class path resource [app.properties]
2016-06-19 10:45:58 INFO  GoEuroTestApp:79 - goeuro application context is prepared
2016-06-19 10:45:58 INFO  GoEuroTestApp:45 - send request for geo data
2016-06-19 10:45:59 INFO  GoEuroTestApp:47 - geo data is received: ["geoData": {"id": "376217", "name": "Berlin", "type": "location", "geoPosition": {"latitude": "52.52437", "longitude": "13.41053" }}, "geoData": {"id": "448103", "name": "Berlingo", "type": "location", "geoPosition": {"latitude": "45.50298", "longitude": "10.04366" }}, "geoData": {"id": "425332", "name": "Berlingerode", "type": "location", "geoPosition": {"latitude": "51.45775", "longitude": "10.2384" }}, "geoData": {"id": "425326", "name": "Bernau bei Berlin", "type": "location", "geoPosition": {"latitude": "52.67982", "longitude": "13.58708" }}, "geoData": {"id": "314826", "name": "Berlin Tegel", "type": "airport", "geoPosition": {"latitude": "52.5548", "longitude": "13.28903" }}, "geoData": {"id": "314827", "name": "Berlin Schönefeld", "type": "airport", "geoPosition": {"latitude": "52.3887261", "longitude": "13.5180874" }}, "geoData": {"id": "334196", "name": "Berlin Hbf", "type": "station", "geoPosition": {"latitude": "52.525589", "longitude": "13.369548" }}, "geoData": {"id": "333977", "name": "Berlin Ostbahnhof", "type": "station", "geoPosition": {"latitude": "52.510972", "longitude": "13.434567" }}]
2016-06-19 10:45:59 INFO  GoEuroTestApp:54 - goeuro report generating .... 
2016-06-19 10:46:01 INFO  GoEuroTestApp:58 - goeuro report is generated
2016-06-19 10:46:01 INFO  GoEuroTestApp:90 - goeuro test finished
bash-3.2$ 


4. goeuro.scv file will be generated with results like these:

Id,Name,Type,Latitude,Longitude
376217,Berlin,location,52.52437,13.41053
448103,Berlingo,location,45.50298,10.04366
425332,Berlingerode,location,51.45775,10.2384
425326,Bernau bei Berlin,location,52.67982,13.58708
314826,Berlin Tegel,airport,52.5548,13.28903
314827,Berlin Schönefeld,airport,52.3887261,13.5180874
334196,Berlin Hbf,station,52.525589,13.369548
333977,Berlin Ostbahnhof,station,52.510972,13.434567
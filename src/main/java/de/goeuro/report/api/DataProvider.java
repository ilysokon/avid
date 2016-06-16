package de.goeuro.report.api;

/**
 * Report Data Provider
 *
 * @param <DATA> Define Data Structure
 */
public interface DataProvider<DATA> {
	DATA getData();
	
}

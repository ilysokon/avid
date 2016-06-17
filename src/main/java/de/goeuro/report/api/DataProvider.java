package de.goeuro.report.api;

/**
 * Report Data Provider
 *
 * @param <DATA> the type of data provider data
 */
public interface DataProvider<DATA> {
	DATA getData();
	
}

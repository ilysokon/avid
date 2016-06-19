package de.goeuro.client;

import java.util.List;

import de.goeuro.model.GeoData;

/**
 * 
 * GoEuro REST Client
 *
 */
public interface GoEuroClient {
	
	/**
	 * Get Geo Data
	 * 
	 * @param city the city geo data get for
	 * @return Geo data for the pointed city 
	 * @throws GoEuroClientException if any error is occurred during the geo data retrieving
	 */
	List<GeoData> getGeoData(String city) throws GoEuroClientException;
}

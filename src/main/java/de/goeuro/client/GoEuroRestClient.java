package de.goeuro.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import de.goeuro.api.GeoData;
import de.goeuro.api.GeoDataResponse;

/**
 * 
 * GoEuro REST Client
 *
 */
@Service
public class GoEuroRestClient {
	private static Logger logger = Logger.getLogger(GoEuroRestClient.class);
	/** default goeuro server url */
	private static String DEFAULT_GOEURO_URL = "http://api.goeuro.com/api/v2/position/suggest/en/";

	public GeoDataResponse getGeoData2(String city) {
		final String uri = DEFAULT_GOEURO_URL + "/" + city;
		RestTemplate restTemplate = new RestTemplate();
		GeoData[] geoData = restTemplate.getForObject(uri,
				(new GeoData[3]).getClass());

		return new GeoDataResponse(Arrays.asList(geoData));
	}

	public List<GeoData> getGeoData(String city) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<GeoData>> rateResponse = restTemplate.exchange(
				DEFAULT_GOEURO_URL + "/" + city, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<GeoData>>() {
		});
		return rateResponse.getBody();
	}
}

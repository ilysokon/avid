package de.goeuro.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
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
	
	/** goeuro server url with default value */
	@Value("#{config['goeuro.url']?:'http://api.goeuro.com/api/v2/position/suggest/en/'}")
	private String url;

	//TODO: exception handling
	public List<GeoData> getGeoData(String city) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<GeoData>> rateResponse = restTemplate.exchange(
				url + "/" + city, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<GeoData>>() {
		});
		return rateResponse.getBody();
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

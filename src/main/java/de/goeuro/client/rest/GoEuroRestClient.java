package de.goeuro.client.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import de.goeuro.api.GeoData;
import de.goeuro.client.GoEuroClient;

/**
 * 
 * GoEuro REST Client
 *
 */
@Service
public class GoEuroRestClient implements GoEuroClient{
	private static Logger logger = Logger.getLogger(GoEuroRestClient.class);
	
	/** goeuro server url */
	@Value("#{config['goeuro.url']?:'http://api.goeuro.com/api/v2/position/suggest/en/'}")
	private String url;

	@Override
	public List<GeoData> getGeoData(String city) throws GoEuroRestClientException{
		RestTemplate restTemplate = new RestTemplate();
		try{
			ResponseEntity<List<GeoData>> response = restTemplate.exchange(
					url + "/" + city, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<GeoData>>() {
			});
			return response.getBody();
		}catch(RestClientException e){
			logger.error(e.getMessage(), e);
			throw new GoEuroRestClientException(e.getMessage(), e);
		}
	}
}

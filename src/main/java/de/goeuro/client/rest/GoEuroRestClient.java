package de.goeuro.client.rest;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import de.goeuro.client.GoEuroClient;
import de.goeuro.model.GeoData;

/**
 * 
 * GoEuro REST Client
 *
 */
@Service
public class GoEuroRestClient implements GoEuroClient{
	private static Logger logger = Logger.getLogger(GoEuroRestClient.class);
	
	@Value("#{config['goeuro.maxRetries']?:5}")
	private int maxRetries;
	
	@Value("#{config['goeuro.retryWaitingTime']?:50}")
	private int retryWaitingTime;
	
	/** goeuro server url */
	@Value("#{config['goeuro.url']?:'http://api.goeuro.com/api/v2/position/suggest/en/'}")
	private String url;

	@Override
	public List<GeoData> getGeoData(String city) throws GoEuroRestClientException{
		RestTemplate restTemplate = new RestTemplate();
		int retries = 0;
		
		ResponseEntity<List<GeoData>> response = null;
		
		do {
			try{
				response = restTemplate.exchange(
					url + "/" + city, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<GeoData>>() {
				});
				return response.getBody();
			} catch(HttpClientErrorException | HttpServerErrorException e){
				logger.info("Error: " + e.getMessage() + ", retries: " + retries);
				if(isRetrievable(e.getStatusCode())){
					retries++;
					try {
						Thread.sleep(retryWaitingTime);
					} catch (InterruptedException e1) {
					}
					// check rerties
					if(retries > maxRetries){
						// all retries are used
						logger.info("GoEuro Server are not reached by " + (retries - 1) + " times");
					    throw new GoEuroRestClientException(e.getMessage(), e);
					}
				} else {
					throw new GoEuroRestClientException(e.getMessage(), e);
				}
			} catch(RestClientException e) {
				// not retriable errors 
				throw new GoEuroRestClientException(e.getMessage(), e);
			}
		} while( true );
	}

	private boolean isRetrievable(HttpStatus statusCode) {
		return statusCode != HttpStatus.BAD_REQUEST;
	}
}

package de.goeuro.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Market Data Statistics unit 
 *
 * _id, name, type, latitude, longitude
 */
public class GeoData {
	
	@JsonProperty("_id") 
	private Integer id;
	
	private String name;
	private String type; 
	
	@JsonProperty("geo_position") 
	private GeoPosition geoPosition; 
	
	public GeoPosition getGeoPosition() {
		return geoPosition;
	}

	public void setGeoPosition(GeoPosition geoPosition) {
		this.geoPosition = geoPosition;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}

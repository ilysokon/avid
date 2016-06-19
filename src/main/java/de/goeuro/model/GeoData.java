package de.goeuro.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Geo Data 
 *
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
	
	@Override
	public String toString(){
		return "\"geoData\": {"
				+ "\"id\": " + "\""+ id + "\", "
				+ "\"name\": " + "\""+ name + "\", "
				+ "\"type\": " + "\""+ type + "\", "
				+ geoPosition.toString()
				+ "}";
	}
}

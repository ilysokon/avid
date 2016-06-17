package de.goeuro.api;

/**
 * 
 * Geo Position
 *
 */
public class GeoPosition {
	
	private Double latitude; 
	
	private Double longitude;

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	@Override
	public String toString(){
		return "\"geoPosition\": {"
				+ "\"latitude\": " + "\"" + latitude + "\", "
				+ "\"longitude\": " + "\"" + longitude + "\" "
				+ "}";
	}
}

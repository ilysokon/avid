package de.goeuro.api;

import java.util.Collection;

public class GeoDataResponse {
	private Collection<GeoData> geoData;
	
	public GeoDataResponse(Collection<GeoData> geoData) {
		this.geoData = geoData;
	}

	public Collection<GeoData> getGeoData() {
		return geoData;
	}

	public void setGeoData(Collection<GeoData> geoData) {
		this.geoData = geoData;
	}
}

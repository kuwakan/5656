package casestudy;

import java.sql.Timestamp;

public class Position {

	private String modes;



	private float lat;

	private float lng;

	private float alt;


	private Timestamp timestamp;

	public Position(String modes,  float lat,  float lng, float alt, Timestamp timestamp){
		this.modes = modes;
		this.lat = lat;
		this.lng = lng;
		this.alt = alt;
		this.timestamp = timestamp;
	}


	public String getModes() {
		return modes;
	}

	public void setModes(String modes) {
		this.modes = modes;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLng() {
		return lng;
	}

	public void setLng(float lng) {
		this.lng = lng;
	}

	public float getAlt() {
		return alt;
	}

	public void setAlt(float alt) {
		this.alt = alt;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}


}

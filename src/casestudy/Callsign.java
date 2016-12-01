package casestudy;

public class Callsign {



	private String callsign;

	private String modes;

	public Callsign(String modes, String callsign) {
		this.modes = modes;
		this.callsign = callsign;
	}




	public String getModes() {
		return modes;
	}


	public void setModes(String modes) {
		this.modes = modes;
	}


	public String getCallsign() {
		return callsign;
	}


	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}


}

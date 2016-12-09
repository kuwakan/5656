package casestudy;

public class CallSignReadDB {



	private String callsign;

	private String modes;

	public CallSignReadDB(String modes, String callsign) {
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

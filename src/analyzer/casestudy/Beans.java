package casestudy;

import java.io.Serializable;

public class Beans implements Serializable{
	// private static final long serialVersionUID = 1L;
	 private AircraftSerch acs;
	 private RealData rd;
	 private boolean isIn = false;
	 private boolean isXml = false;


	public AircraftSerch getAcs() {
		return acs;
	}
	public void setAcs(AircraftSerch acs) {
		this.acs = acs;
	}
	public RealData getRd() {
		return rd;
	}
	public void setRd(RealData rd) {
		this.rd = rd;
	}

	public boolean isIn() {
		return isIn;
	}
	public void setIn(boolean isIn) {
		this.isIn = isIn;
	}
	public boolean isXml() {
		return isXml;
	}
	public void setXml(boolean isXml) {
		this.isXml = isXml;
	}
}

package casestudy;

import java.text.SimpleDateFormat;

public class DB_Item_PlanePosition {

	private long timeStamp_;
	private String modeSAddress_;
	private PlanePosition planePosition_;

	DB_Item_PlanePosition(String modeSAddress, PlanePosition planePosition){
		this.timeStamp_ = System.currentTimeMillis();
		this.modeSAddress_ = modeSAddress;
		this.planePosition_ = planePosition;
	}

	public long getTimeStamp() {
		return timeStamp_;
	}

	public String getModeSAddress() {
		return modeSAddress_;
	}

	public PlanePosition getPlanePosition() {
		return planePosition_;
	}


	@Override
	public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:MM:ss:SSS");
		return ("PlanePosition," + sdf.format(timeStamp_) + "," + modeSAddress_ + "," + planePosition_.toString());
	}

}

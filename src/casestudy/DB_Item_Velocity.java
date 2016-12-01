package casestudy;

import java.text.SimpleDateFormat;

public class DB_Item_Velocity {

	private long timeStamp_;
	private String modeSAddress_;
	private Velocity velocity_;

	DB_Item_Velocity(String modeSAddress, Velocity velocity){
		this.timeStamp_ = System.currentTimeMillis();
		this.modeSAddress_ = modeSAddress;
		this.velocity_ = velocity;
	}

	public long getTimeStamp() {
		return timeStamp_;
	}

	public String getModeSAddress() {
		return modeSAddress_;
	}

	public Velocity getVelocity() {
		return velocity_;
	}


	@Override
	public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:MM:ss:SSS");
		return ("Velocity," + sdf.format(timeStamp_) + "," + modeSAddress_ + "," + velocity_.toString());
	}

}

package casestudy;

import java.text.SimpleDateFormat;

/**
 * 自作クラス タイムスタンプ、ModeSアドレス、速度情報（Velocityオブジェクト）をもつ
 */
public class DB_Item_Velocity {

	private long timeStamp_;
	private String modeSAddress_;
	private Velocity velocity_;

	/**
	 * コンストラクタ
	 * @param modeSAddress
	 * @param velocity
	 */
	DB_Item_Velocity(String modeSAddress, Velocity velocity){
		this.timeStamp_ = System.currentTimeMillis();
		this.modeSAddress_ = modeSAddress;
		this.velocity_ = velocity;
	}

	/**
	 * タイムスタンプのgetter
	 * @return タイムスタンプ
	 */
	public long getTimeStamp() {
		return timeStamp_;
	}

	/**
	 * ModeSアドレスのgetter
	 * @return ModeSアドレス
	 */
	public String getModeSAddress() {
		return modeSAddress_;
	}

	/**
	 * 速度情報のgetter
	 * @return 速度情報（Velocityオブジェクト）
	 */
	public Velocity getVelocity() {
		return velocity_;
	}


	@Override
	public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss:SSS");
		return ("Velocity," + sdf.format(timeStamp_) + "," + modeSAddress_ + "," + velocity_.toString());
	}

}

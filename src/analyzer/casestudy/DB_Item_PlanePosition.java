package casestudy;

import java.text.SimpleDateFormat;

/**
 * 自作クラス タイムスタンプ、ModeSアドレス、位置情報（PlanePosiionオブジェクト）をもつ
 */
public class DB_Item_PlanePosition {

	private long timeStamp_;
	private String modeSAddress_;
	private PlanePosition planePosition_;

	/**
	 * コンストラクタ
	 * @param modeSAddress
	 * @param planePosition
	 */
	DB_Item_PlanePosition(String modeSAddress, PlanePosition planePosition){
		this.timeStamp_ = System.currentTimeMillis();
		this.modeSAddress_ = modeSAddress;
		this.planePosition_ = planePosition;
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
	 * 位置情報のgetter
	 * @return 位置情報（PlanePositionオブジェクト）
	 */
	public PlanePosition getPlanePosition() {
		return planePosition_;
	}


	@Override
	public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss:SSS");
		return ("PlanePosition," + sdf.format(timeStamp_) + "," + modeSAddress_ + "," + planePosition_.toString());
	}

}

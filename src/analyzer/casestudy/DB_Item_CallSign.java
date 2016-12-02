package casestudy;

import java.text.SimpleDateFormat;

/**
 * 自作クラス タイムスタンプ、ModeSアドレス、コールサインをもつ
 */
public class DB_Item_CallSign {

	/** タイムスタンプ */
	private long timeStamp_;

	/** ModeSアドレス */
	private String modeSAddress_;

	/** コールサイン */
	private String callSign_;

	/**
	 * コンストラクタ
	 * @param modeSAddress
	 * @param callSign
	 */
	DB_Item_CallSign(String modeSAddress, String callSign){
		this.timeStamp_ = System.currentTimeMillis();
		this.modeSAddress_ = modeSAddress;
		this.callSign_ = callSign;
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
	 * コールサインのgetter
	 * @return コールサイン
	 */
	public String getCallSign() {
		return callSign_;
	}


	@Override
	public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss:SSS");
		return ("CallSign," + sdf.format(timeStamp_) + "," + modeSAddress_ + "," + callSign_);
	}

}

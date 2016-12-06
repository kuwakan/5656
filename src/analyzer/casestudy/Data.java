package casestudy;

/**
 * Even又はOddデータをSBS-3受信データ(バイナリ形式)のまま、格納する。<br>
 * タイムスタンプは格納時の時間
 */
public class Data {

	private String data;
	private long timeStamp;

	/**
	 * コンストラクタ
	 */
	Data(String data){
		this.data = data;
		this.timeStamp = System.currentTimeMillis();
	}

	/**
	 * SBS-3受信データ(バイナリ形式)ゲッター
	 * @return data SBS-3受信データ(バイナリ形式)
	 */
	public String getData(){
		return this.data;
	}

	/**
	 * タイムスタンプゲッター
	 * @return timestamp(ミリ秒)格納時間
	 */
	public long getTimeStamp(){
		return this.timeStamp;
	}

	/**
	 * SBS-3受信データ(バイナリ形式)に含まれるモードSアドレスのゲッター
	 * @return String モードS
	 */
	public String getModeS(){
		return Integer.toHexString(Integer.parseInt(data.substring(64,64+24), 2));
	}

	/**
	 * SBS-3受信データ(バイナリ形式)に含まれるタイムコード(1bit)のゲッター
	 * @return String タイムコード(0又は1)
	 */
	public String getTime(){
		return data.substring(108,108+1);
	}

	/**
	 * SBS-3受信データ(バイナリ形式)に含まれるタイムコード(1bit)とモードSアドレスがどちらも一致しているか調べる
	 * @param rawData SBS-3受信データ(バイナリ形式)
	 * @return 一致:true 不一致:false
	 */
	public boolean timeAndModeSEquals(String rawData){
		return (this.timeEquals(rawData) && this.modeSEquals(rawData));
	}

	/**
	 * SBS-3受信データ(バイナリ形式)に含まれるタイムコード(1bit)が一致しているか調べる
	 * @param rawData SBS-3受信データ(バイナリ形式)
	 * @return 一致:true 不一致:false
	 */
	private boolean timeEquals(String rawData){
		return rawData.substring(108, 108+1).equals(this.getTime());
	}

	/**
	 * SBS-3受信データ(バイナリ形式)に含まれるモードSアドレスが一致しているか調べる
	 * @param rawData SBS-3受信データ(バイナリ形式)
	 * @return 一致:true 不一致:false
	 */
	private boolean modeSEquals(String rawData){
		return ADS_B_Analyzer.modeS_Analyze(rawData).equals(this.getModeS());
	}

}

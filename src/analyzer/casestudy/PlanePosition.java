package casestudy;

/**
 * 自作クラス 位置情報をもつ
 */
public class PlanePosition {

	/** 緯度 */
	private double lat_;

	/** 経度 */
	private double lon_;

	/** 高度 */
	private double alt_;

	/**
	 * コンストラクタ
	 * @param lat
	 * @param lon
	 * @param alt
	 */
	PlanePosition(double lat, double lon, double alt){
		this.lat_ = lat;
		this.lon_ = lon;
		this.alt_ = alt;
	}

	/**
	 * 緯度のgetter
	 * @return lat_
	 */
	public double getLat() {
		return lat_;
	}

	/**
	 * 経度のgetter
	 * @return lon_
	 */
	public double getLon() {
		return lon_;
	}

	/**
	 * 高度のgetter
	 * @return alt_
	 */
	public double getAlt() {
		return alt_;
	}


	@Override
	public String toString(){
		return (lat_ + "," + lon_ + "," + alt_);
	}

}

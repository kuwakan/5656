package casestudy;

import static java.lang.Math.*;

/**
 * 解析手法クラス<br>
 * Nic(精度計算)は使用予定がなかったため未実装
 */
public class LatLonAltAnalyzer {


	/**
	 * 地理緯度の区切り数
	 */
	private static final int NZ = 15;

	/**
	 * mod(x,y)を返す
	 * @param x
	 * @param y
	 * @return mod
	 */
	public static double mod(double x,double y) {
		return x-y*floor(x/y);
	}

	/**
	 * 緯度における経度ゾーン数NL
	 * 参考:ADS-Bフォーマットp4
	 *	極地に近い緯度では、東西方向に分割したゾーン数は少なく
	 *		Lat &gt; +86	or Lat &lt; -86	⇒	NL=1
	 *	赤道に近い緯度では、東西方向に分割したゾーン数は少なく
	 *		Lat = 0						⇒	NL=59
	 * @param lat 緯度
	 * @return NumberLat 緯度における経度ゾーンの数
	 */
	public static int nl(double lat) {
		if((lat > 87)||(lat < -87)){
			return 1;
		}else if((lat > 86.5)||(lat < -86.5)){
			return 2;
		}else{
			return (int)floor(2*PI/acos(1-(1-cos(PI/(2*NZ)))/pow(cos(PI/180*lat),2.0)));
		}
	}

	/**
	 * dataからCPR(Compact Position Reporting)形式のLon(経度)を抽出
	 * @param data data SBS-3受信データ(バイナリ形式)
	 * @return lon	CPR(Compact Position Reporting)形式の経度
	 */
	public static double lon_cpr(String data) {
		return (double)Integer.parseInt(data.substring(127,127+17), 2) /131072;
	}

	/**
	 * dataからCPR(Compact Position Reporting)形式のLat(緯度)を抽出
	 * @param data SBS-3受信データ(バイナリ形式)
	 * @return lat	CPR(Compact Position Reporting)形式の緯度
	 */
	public static double lat_cpr(String data) {
		return (double)Integer.parseInt(data.substring(110,110+17), 2)/131072;
	}

	/**
	 * 緯度ゾーン番号jの算出
	 * 参考:ADS-Bフォーマットp5
	 * 	j = floor(59 × Lat_cprE - 60 Lat_cprO + 1/2)
	 * @param dataE SBS-3受信したevenデータ(バイナリ形式)
	 * @param dataO SBS-3受信したoddデータ(バイナリ形式)
	 * @return 緯度ゾーン番号を返す
	 */
	public static double latIndexJ(String dataE, String dataO) {
		return floor(59.0 * lat_cpr(dataE) - 60.0 * lat_cpr(dataO) + 0.5);
	}

	/**
	 * Even(偶数)における南北方向における緯度帯の幅
	 */
	private static final double DLAT_E = (double)360 / (4 * NZ);
	/**
	 * Odd(奇数)における南北方向における緯度帯の幅
	 */
	private static final double DLAT_O= (double)360 / (4 * NZ - 1);


	public static double latE(String dataE, String dataO) {
		double lat_E = DLAT_E * (mod(latIndexJ(dataE, dataO), 60) + lat_cpr(dataE));

		if(lat_E >= 270) lat_E -= 360;
		return lat_E;
	}
	public static double latO(String dataE, String dataO) {
		double lat_O = DLAT_O * (mod(latIndexJ(dataE, dataO), 59) + lat_cpr(dataO));

		if(lat_O >= 270) lat_O -= 360;
		return lat_O;
	}

	/**
	 * モードSアドレスが一致したEven(偶数)、Odd(奇数)データから緯度を返す
	 * @param dataE(偶数) SBS-3受信したevenデータ(バイナリ形式)
	 * @param dataO(奇数) SBS-3受信したoddデータ(バイナリ形式)
	 * @param evenNewThanOdd EvenがOddよりタイムスタンプが新しい場合、true、それ以外はfalse
	 * @return double lat 緯度
	 */
	public static double calcLat(String dataE, String dataO, boolean evenNewThanOdd) {
		double lat;
		if(evenNewThanOdd){
			lat = latE(dataE, dataO);
		}else{
			lat = latO(dataE, dataO);
		}
		return lat;
	}

	/**
	 * モードSアドレスが一致したEven(偶数)、Odd(奇数)データから経度を返す
	 * @param dataE(偶数) SBS-3受信したevenデータ(バイナリ形式)
	 * @param dataO(奇数) SBS-3受信したoddデータ(バイナリ形式)
	 * @param evenNewThanOdd EvenがOddよりタイムスタンプが新しい場合、true、それ以外はfalse
	 * @return double lon 経度
	 */
	public static double calcLon(String dataE, String dataO, boolean evenNewThanOdd) {
		double lon;
		double lonE = lon_cpr(dataE);
		double lonO = lon_cpr(dataO);

		if(evenNewThanOdd){
			double ni = max(nl(latE(dataE, dataO)), 1.0);
			double dLon = 360/ni;
			double m = floor(lonE*(nl(latE(dataE, dataO))-1)-lonO*nl(latE(dataE, dataO))+0.5);
			lon = dLon*(mod(m,ni)+lonE);
		}else{
			double ni = max(nl(latO(dataE, dataO))-1, 1.0);
			double dLon = 360/ni;
			double m = floor(lonE*(nl(latO(dataE, dataO))-1)-lonO*nl(latO(dataE, dataO))+0.5);
			lon = dLon*(mod(m,ni)+lonO);
		}
		if(lon >= 180.0){
			lon = lon - 360;
		}
		return lon;
	}

	/**
	 * ADS-Bメッセージの48bit目(ここでは103bit目)がたっているか
	 * @param data
	 * @return 0 or 1
	 */
	private static int unitBit(String data) {
		int unitbit = Integer.parseInt(data.substring(103,104),2);
		return unitbit;
	}

	/**
	 * モードSアドレスが一致したEven(偶数)、Odd(奇数)データから高度を返す
	 * @param dataE(偶数) SBS-3受信したevenデータ(バイナリ形式)
	 * @param dataO(奇数) SBS-3受信したoddデータ(バイナリ形式)
	 * @param evenNewThanOdd EvenがOddよりタイムスタンプが新しい場合、true、それ以外はfalse
	 * @return int alt 高度
	 */
	public static int calcAlt(String dataE, String dataO, boolean evenNewThanOdd) {
		int alt;
		if(evenNewThanOdd){
			alt = calc_alt(dataE);
		}else{
			alt = calc_alt(dataO);
		}
		return alt;
	}
	/**
	 * 高度解析
	 * @param data SBS-3受信データ(バイナリ形式)
	 * @return 高度 単位フィート
	 */
	public static int calc_alt(String data) {
		int n = 0;
		String frontbit = data.substring(96, 96+7);
		String backbit = data.substring(103, 103+4);
		String altbin = frontbit + backbit;

		//高度を何ft基準で解析するか判別
		if(unitBit(data) == 1) n = 25;
		else n = 100;

		int altitude = Integer.parseInt(altbin, 2) * n - 1000;

		return altitude;
	}

	/**
	 * モードSアドレスが一致したEven(偶数)、Odd(奇数)データから緯度経度高度を保存したPlanePositionを返す
	 * @param dataE(偶数) SBS-3受信したevenデータ(バイナリ形式)
	 * @param dataO(奇数) SBS-3受信したoddデータ(バイナリ形式)
	 * @param evenNewThanOdd EvenがOddよりタイムスタンプが新しい場合、true、それ以外はfalse
	 * @return PlanePosition 緯度経度高度を格納したオブジェクト
	 */
	public static PlanePosition calc_Position(String dataE, String dataO, boolean evenNewThanOdd){
		double lat = calcLat(dataE, dataO, evenNewThanOdd);

		//チェック NL(Lat_E)とNL(Lat_O)を比べる 同様の値にならない時、計算を中止する
		if((int)latE(dataE, dataO) != (int)latO(dataE, dataO)) return null;

		double lon = calcLon(dataE, dataO, evenNewThanOdd);
		int alt = calcAlt(dataE, dataO, evenNewThanOdd);

		return  new PlanePosition(lat, lon, alt);
	}

}

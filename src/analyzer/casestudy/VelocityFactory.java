package casestudy;
/**
 * SBS-3受信データ(バイナリ形式)からVelocity型のデータを作るクラス
 * Velocity型は、水平方向の速度・方位と垂直方向の速度・向きからなるデータ
 */
public class VelocityFactory {
	/**
	 * dataから水平方向の速度・方位と垂直方向の速度・向きを解析
	 * @param data SBS-3受信データ(バイナリ形式)
	 * @return Velocity(方位、速度のオブジェクト)
	 */
	public static Velocity calc_velocity(String data) {
		return new Velocity(calcVerticalSign(data), calcVerticalVerocity(data),calcHorizonDeg(data), calcHorizonVelocity(data));
	}

	/**
	 * データから垂直方向の向き抽出。
	 * @param data SBS-3受信データ(バイナリ形式)
	 * @return 0:Up	1:Down
	 */
	public static int calcVerticalSign(String data) {
		int s_Vr;
		s_Vr = Integer.parseInt(data.substring(124,124+1), 2);
		return s_Vr;
	}

	/**
	 * データから垂直方向の速度を計算。
	 * @param data SBS-3受信データ(バイナリ形式)
	 * @return 速度(kn/h、、絶対値)
	 */
	public static double calcVerticalVerocity(String data) {
		double vr;
		vr = Integer.parseInt(data.substring(125,125+8), 2);
		return vr;
	}

	/**
	 * データから水平方向の方位を計算。
	 * @param data SBS-3受信データ(バイナリ形式)
	 * @return 方位(度)
	 */
	public static double calcHorizonDeg(String data) {
		double deg;
		deg = Math.atan(calcEWVelocity(data)/calcNSVelocity(data)) * 360.0 / (2 * Math.PI );

		if(deg < 0){
			deg += 360;
		}
		return deg;
	}

	/**
	 * データから水平方向の速度を計算。
	 * @param data SBS-3受信データ(バイナリ形式)
	 * @return 速度(kn/h、、絶対値)
	 */
	public static double calcHorizonVelocity(String data) {
		double vel;
		vel = Math.sqrt(Math.pow(calcEWVelocity(data),2.0) + Math.pow(calcNSVelocity(data), 2.0));
		return vel;
	}

	/**
	 * データから南北方向における速度を計算。
	 * @param data SBS-3受信データ(バイナリ形式)
	 * @return 南北方向における速度(kn/h)
	 */
	public static double calcNSVelocity(String data) {
		double v_NS;
		if(signNS(data)==1){	//北から南の場合
			v_NS = (-1) * (Integer.parseInt(data.substring(113,113+10), 2) - 1);
		}else{					//南から北の場合
			v_NS = Integer.parseInt(data.substring(113,113+10), 2) - 1;
		}
		return v_NS;
	}

	/**
	 * データから東西方向における速度を計算。
	 * @param data SBS-3受信データ(バイナリ形式)
	 * @return 東西方向における速度(kn/h)
	 */
	public static double calcEWVelocity(String data) {
		double v_EW;
		if(signEW(data)==1){	//東から西の場合
			v_EW = (-1) * (Integer.parseInt(data.substring(102,102+10), 2) - 1);
		}else{					//西から東の場合
			v_EW = Integer.parseInt(data.substring(102,102+10), 2) - 1;
		}
		return v_EW;
	}

	/**
	 * データから南北方向の向きを返す
	 * @param data SBS-3受信データ(バイナリ形式)
	 * @return 0:南から北	1:北から南
	 */
	public static int signNS(String data) {
		return Integer.parseInt(data.substring(112,112+1), 2);
	}

	/**
	 * データから東西方向の向きを返す
	 * @param data SBS-3受信データ(バイナリ形式)
	 * @return 0:西から東	1:東から西
	 */
	public static int signEW(String data) {
		return Integer.parseInt(data.substring(101,101+1), 2);
	}
}

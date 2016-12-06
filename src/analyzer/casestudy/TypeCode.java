package casestudy;

import static casestudy.ADS_B_Analyzer.*;
import static casestudy.CallSignFactory.*;

/**
 * タイプコードを判別するクラス
 */
public enum TypeCode {

	CALL_SIGN,
	VELOCITY,
	PLANE_POSITION,
	OTHER;

	/**
	 * タイプコードを判別し、結果を返す
	 * @param binaryRawData
	 * @return CALL_SIGN
	 * @return VELOCITY
	 * @return PLANE_POSITION
	 * @return OTHER
	 */
	public static TypeCode createTypeCode(String binaryRawData){
		if(judgeCallSign(binaryRawData)  && callSignCheck(binaryRawData)) return CALL_SIGN;
		else if(judgeVelocity(binaryRawData)) return VELOCITY;
		else if(judgePosition(binaryRawData)) return PLANE_POSITION;
		else return OTHER;
	}

	/**
	 * タイプコード判別(Position)
	 * @param binaryRawData
	 * @return タイプコードが9～18のときtrue
	 */
	private static boolean judgePosition(String binaryRawData) {
		return 9 <= tc_Analyze(binaryRawData) && tc_Analyze(binaryRawData) <= 18;
	}

	/**
	 * タイプコード判別(Velocity)
	 * @param binaryRawData
	 * @return タイプコードが9～18のときtrue
	 */
	private static boolean judgeVelocity(String binaryRawData) {
		return tc_Analyze(binaryRawData) == 19;
	}

	/**
	 * タイプコード判別(CallSign)
	 * @param binaryRawData
	 * @return タイプコードが9～18のときtrue
	 */
	private static boolean judgeCallSign(String binaryRawData) {
		return 1 <= tc_Analyze(binaryRawData) && tc_Analyze(binaryRawData) <= 4;
	}

	/**
	 * コールサインの不正データを破棄する
	 * @param binaryRawData
	 * @return データが正しい場合true
	 */
	private static boolean callSignCheck(String binaryRawData) {
		String str = calcCallSign(binaryRawData);
		for(int i = 0; i < str.length(); i++){
			if((48 <= str.charAt(i) && str.charAt(i) <= 57) || (65 <= str.charAt(i) && str.charAt(i) <= 90)){
				return true;
			}
		}
		return false;
	}

}

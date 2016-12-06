package casestudy;

import static casestudy.ADS_B_Analyzer.*;
import static casestudy.CallSignFactory.*;

public enum TypeCode {
	CALL_SIGN,
	VELOCITY,
	PLANE_POSITION,
	OTHER;
	public static TypeCode createTypeCode(String binaryRawData){
		if(judgeCallSign(binaryRawData) && callSignCheck(binaryRawData)) return CALL_SIGN;
		else if(judgeVelocity(binaryRawData)) return VELOCITY;
		else if(judgePosition(binaryRawData)) return PLANE_POSITION;
		else return OTHER;
	}

	private static boolean judgePosition(String rawData) {
		return 9 <= tc_Analyze(rawData) && tc_Analyze(rawData) <= 18;
	}
	private static boolean judgeVelocity(String rawData) {
		return tc_Analyze(rawData) == 19;
	}
	private static boolean judgeCallSign(String rawData) {
		return 1 <= tc_Analyze(rawData) && tc_Analyze(rawData) <= 4;
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

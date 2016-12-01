package casestudy;

import static casestudy.ADS_B_Analyzer.*;


public enum TypeCode {
	CALL_SIGN,
	VELOCITY,
	PLANE_POSITION,
	OTHER;
	public static TypeCode createTypeCode(String rawData){
		if(judgeCallSign(rawData)) return CALL_SIGN;
		else if(judgeVelocity(rawData)) return VELOCITY;
		else if(judgePosition(rawData)) return PLANE_POSITION;
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


}

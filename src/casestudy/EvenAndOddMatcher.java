package casestudy;

import static casestudy.ADS_B_Analyzer.*;
import static casestudy.CallSignFactory.*;
import static casestudy.PlanePositionFactory.*;
import static casestudy.TypeCode.*;

import java.util.ArrayList;
/**
 * ADSBからのデータを解析
 */
public class EvenAndOddMatcher {

	static final long DELETE_TIME = 1000 * 10;//削除のしきい値(ms)
	static ArrayList<Data> evenDataList = new ArrayList<Data>();
	static ArrayList<Data> oddDataList = new ArrayList<Data>();

	public static String analyzeData(String hexRawData){

		if(hexRawDataCheck(hexRawData)){

			String binaryRawData = HexToBinary.hexToBinary(hexRawData);
			PlanePosition planePosition = null;
			if(parityCheck(binaryRawData)){
				if(judgedADS_B_Data(binaryRawData)){

					if(			createTypeCode(binaryRawData) == CALL_SIGN){
						DB_Item_Generator.dB_Item_CallSign_Generate(modeS_Analyze(binaryRawData), calcCallSign(binaryRawData));
					}else if(	createTypeCode(binaryRawData) == VELOCITY){
						DB_Item_Generator.dB_Item_Velocity_Generate(modeS_Analyze(binaryRawData), VelocityFactory.calc_velocity(binaryRawData));
					}else if(	createTypeCode(binaryRawData) == PLANE_POSITION){
						planePosition = rawDataToPlanePosition(binaryRawData);
						if(!(planePosition == null)){
							DB_Item_Generator.dB_Item_PlanePosition_Generate(modeS_Analyze(binaryRawData), planePosition);
						}
					}
				}
			}
		}
		return null;
	}

	private static boolean judgedADS_B_Data(String rawData) {
		return createDownLinkFormatNo(rawData) == 17;
	}

	private static int createDownLinkFormatNo(String data) {
		return Integer.parseInt(data.substring(56,56+5), 2);
	}

	private static boolean hexRawDataCheck(String hexRawData){
		if(hexRawData.length() == 75){
			return hexRawData.substring(0,0+8).equals("10 02 01") && hexRawData.substring(63,63+5).equals("10 03");
		}
		return  false;
	}

}
package casestudy;

import static casestudy.ADS_B_Analyzer.*;
import static casestudy.CallSignFactory.*;
import static casestudy.PlanePositionFactory.*;
import static casestudy.TypeCode.*;

import java.util.ArrayList;

/**
 * ADSBからのデータを解析するクラス
 */
public class EvenAndOddMatcher {

	/** DELETE_TIME=削除のしきい値(ms) */
	static final long DELETE_TIME = 1000 * 10;

	/** evenデータを格納 */
	static ArrayList<Data> evenDataList = new ArrayList<Data>();

	/** oddデータを格納 */
	static ArrayList<Data> oddDataList = new ArrayList<Data>();

	/**
	 * 受信した受信した生データ(16進数表記かつスペースで区切られている)を解析し、各DB-Itemを作る
	 * @param hexRawData
	 * @return 各DB_Item
	 */
	public static String analyzeData(String hexRawData){

		//受信した生データを解析処理可能なものか判別する
		if(hexRawDataCheck(hexRawData)){

			String binaryRawData = HexToBinary.hexToBinary(hexRawData);
			PlanePosition planePosition = null;

			//受信データのパリティチェック
			if(parityCheck(binaryRawData)){

				//ADS-Bデータか判別
				if(judgedADS_B_Data(binaryRawData)){

					if(			createTypeCode(binaryRawData) == CALL_SIGN){
						DB_Item_Generator.dB_Item_Generate(modeS_Analyze(binaryRawData), calcCallSign(binaryRawData));
					}else if(	createTypeCode(binaryRawData) == VELOCITY){
						DB_Item_Generator.dB_Item_Generate(modeS_Analyze(binaryRawData), VelocityFactory.calc_velocity(binaryRawData));
					}else if(	createTypeCode(binaryRawData) == PLANE_POSITION){
						planePosition = rawDataToPlanePosition(binaryRawData);
						if(!(planePosition == null)){
							DB_Item_Generator.dB_Item_Generate(modeS_Analyze(binaryRawData), planePosition);
						}
					}
				}
			}
		}
		return null;
	}



	/**
	 * SBS-3から受信した生データ(16進数表記かつスペースで区切られている)のフォーマットが正しいか判別
	 * @param hexRawData
	 * @return フォーマットが正しいときtrueを返す
	 */
	private static boolean hexRawDataCheck(String hexRawData){
		if(hexRawData.length() == 75){
			return hexRawData.substring(0,0+8).equals("10 02 01") && hexRawData.substring(63,63+5).equals("10 03");
		}
		return  false;
	}

	/**
	 * ダウンリンクフォーマットが17(ADS-Bデータ)か判別する
	 * @param rawData
	 * @return DF(ダウンリンクフォーマット)が17のときtrueを返す
	 */
	private static boolean judgedADS_B_Data(String rawData) {
		return createDownLinkFormatNo(rawData) == 17;
	}

	/**
	 * 受信データ(バイナリ形式)のDF(ダウンリンクフォーマット)を返す
	 * @param data
	 * @return DF(ダウンリンクフォーマット)
	 */
	private static int createDownLinkFormatNo(String data) {
		return Integer.parseInt(data.substring(56,56+5), 2);
	}

}
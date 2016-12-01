package casestudy;

/**
 * SBS-3受信データ(バイナリ形式)からCallSignをString型のデータで変換するクラス
 */
public class CallSignFactory {
	/**
	 * 文字コードデータ抽出した数字をこの文字コードで変換する
	 * 参考:ADS-Bフォーマットp2
	 */
	private static final String MOJI_CODE = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ#####_###############0123456789######";
	/**
	 * dataからコールサイン解析
	 * @param data SBS-3受信データ(バイナリ形式)
	 * @return コールサイン
	 */
	public static String calcCallSign(String data){

		StringBuilder callSignBuilder = new StringBuilder();

		for(int i = 96; i < 143; i += 6){
			callSignBuilder.append(MOJI_CODE.charAt(Integer.parseInt(data.substring(i,i+6), 2)));
		}

		return callSignBuilder.toString();
	}
}

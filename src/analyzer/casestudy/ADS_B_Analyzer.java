package casestudy;

/**
 * タイプコードがDF17のデータを解析するクラス
 */
public class ADS_B_Analyzer {

	/**
	 * dataからTypeCcodeを抽出
	 * @param data SBS-3受信データ(バイナリ形式)
	 * @return タイプコード番号
	 */
	public static int tc_Analyze(String data) {
		return Integer.parseInt(data.substring(88,88+5), 2);
	}

	/**
	 * dataからモードSアドレスを抽出
	 * @param data SBS-3受信データ(バイナリ形式)
	 * @return モードSアドレス
	 */
	public static String modeS_Analyze(String data) {
		 return Integer.toHexString(Integer.parseInt(data.substring(64,64+24), 2));
	}

	/**
	 * dataにパリティチェックのエラーがあるか確認
	 * @param data SBS-3受信データ(バイナリ形式)
	 * @return パリティチェックの結果
	 */
	public static boolean parityCheck(String data){
		return Integer.parseInt(data.substring(144, 168),2) == 0;
	}

}

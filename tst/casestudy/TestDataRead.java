package casestudy;

import static casestudy.HexToBinary.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * テストデータ読み取り用クラス
 * @author anque
 *
 */
public class TestDataRead {
	/**
	 * 対象テストデータから指定した行を読み出す
	 * @param filePath	対象ファイルのパス(省略可：省略時はtest1000.txt)
	 * @param lineNo	対象行(1行目～)
	 * @return	テストデータの指定した行の文字列
	 */
	public static String fileReadLine(String filePath,int lineNo) {
		StringBuffer sb = new StringBuffer();
		FileReader fr = null;
		BufferedReader br = null;
		int nowLine = 1;
		try {
			fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				if(nowLine == lineNo)sb.append(line);
				nowLine++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
			e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public static String fileReadLine(int lineNo) {
		return fileReadLine("test1000.txt",lineNo);
	}
	public static String fileReadLineBinary(int lineNo) {
		return hexToBinary(fileReadLine("test1000.txt",lineNo));
	}


}

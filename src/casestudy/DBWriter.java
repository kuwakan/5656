package casestudy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;

/**
 * DBのテーブルをCSVファイルで出力するクラス
 */
public class DBWriter {

	private Connection con;
	private long timeStamp_;
    private SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");

	public DBWriter(Connection con) {
		this.con = con;
		this.timeStamp_ = System.currentTimeMillis();

	}

	/**
	 * 保存場所とファイル名を指定すると、DBのテーブルからデータを読み込みCSVファイルで出力
	 * @param type 出力するテーブル
	 * @throws Exception
	 */
	protected void createCSV(String type)  throws Exception {

		RepositoryChooser repoChooser = new RepositoryChooser();
		repoChooser.actionPerformed(type + "テーブル");

		String fileName = repoChooser.getDir() + sdf.format(timeStamp_) + " " + type + " " + repoChooser.getFileName() + ".csv";
		String sql = "SELECT * FROM " + type;

		Vector<String> datas = new Vector<String>(); // データ格納用の可変長配列


		//SQLを発行する
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		//ファイルをオープンする（ようなもの）
		File file = new File(fileName);
		FileWriter fw = new FileWriter(file);

		// データベースの列名定義を取得する
		ResultSetMetaData rsmd = rs.getMetaData();
		int cnt = rsmd.getColumnCount();
		for (int idx = 1; idx <= cnt; idx++) {
			String colName = rsmd.getColumnName(idx);
			datas.add(colName);
		}
		// 列名をファイルに書き出す
		writeData(fw, datas);

		//データベースの各列のデータを取得する
		while (rs.next()) {
			for (int idx = 1; idx <= cnt; idx++) {
				String data = rs.getString(idx);
				datas.add(data);
			}
			// 各列のデータをファイルに書き出す
			writeData(fw, datas);
			}
			// ファイルを閉じる
			fw.close();
		}

	protected void writeData(FileWriter fw, Vector<String> datas) throws IOException {

		String buf = ""; // ファイルに書き出す文字列を入れるところ

		//データが空でなかった場合のみ実行
		if (datas != null && !datas.isEmpty()) {
			for (int idx = 0; idx < datas.size(); idx++) {
				// 先頭以外で、カンマをつける
				if (idx > 0) {
				buf += ",";
			}
			// 取り出したデータを文字列にくっつける
			buf += (String)datas.get(idx);
			}
			// 行末に改行を入れる
			buf += "\n";
			// できた文字列をファイルに書き出す
			fw.write(buf);
			// もらったデータを空にする
			datas.clear();
		}

	}

}
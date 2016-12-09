package casestudy;

import java.sql.Connection;

import org.junit.Test;

public class ExportCsvFileTest {

	@Test
	public void DBからデータをcsv形式で出力する() {

		Connection   con = null;

		try {

			con = ConnectionManager.getConnection();
			System.out.println("接続完了");

			DBWriter dbWriter = new DBWriter(con);
			dbWriter.createCSV("テストcallsign");
			dbWriter.createCSV("テストvelocity");
			dbWriter.createCSV("テストposition");
			System.out.println("エクスポート完了");

		}catch (Exception e) {
			// 何らかのエラーがあっても表示するのみ
			System.out.println("エラーです");
			e.printStackTrace();
		}

	}

}

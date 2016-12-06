package casestudy;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

public class ResetDBTest {

	@Test
	public void DBをリセットするテスト() {

		Connection   con = null;

		try {

			con = ConnectionManager.getConnection();
			System.out.println("接続完了");

//			ResetDB resetDB = new ResetDB(con);
//			resetDB.resetDB();
//			System.out.println("全テーブルリセット完了");

		} catch (SQLException e) {
			e.printStackTrace();
		}


		try {
			DBWriter dbWriter = new DBWriter(con);
			// 引数1をファイル名、引数２をSQL文として扱う
			dbWriter.createCSV("callsign");
		}
		catch (Exception e) {
			// 何らかのエラーがあっても、表示するのみ
			System.out.println("エラーです");
			e.printStackTrace();
		}

	}

}

package casestudy;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * DBの全テーブルをリセットするクラス
 */
public class ResetDB {

	public void resetDB() throws SQLException {

		Connection   con = null;
		con = ConnectionManager.getConnection();
		System.out.println("DB接続完了");

		Statement stmt = null;

		try{

			stmt = con.createStatement();
			stmt.executeUpdate("TRUNCATE TABLE callsign");
			stmt.executeUpdate("TRUNCATE TABLE velocity");
			stmt.executeUpdate("TRUNCATE TABLE position");

		}catch (SQLException e){

			  System.out.println("SQLException:" + e.getMessage());

		}finally{

			if(stmt != null){
				stmt.close();
			}
			System.out.println("全テーブルリセット完了");

		}

	}

}

package casestudy;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * DBの全テーブルをリセットするクラス
 */
public class ResetDB {

	private  Connection con;

	/**	コンストラクタ */
	public ResetDB(Connection con) {
		this.con = con;
	}

	public void resetDB() throws SQLException {

		Statement stmt = null;
		int rs = 0;

		try{

			stmt = con.createStatement();
			rs = stmt.executeUpdate("TRUNCATE TABLE callsign");
			rs = stmt.executeUpdate("TRUNCATE TABLE velocity");
			rs = stmt.executeUpdate("TRUNCATE TABLE position");

		}catch (SQLException e){

			  System.out.println("SQLException:" + e.getMessage());

		}finally{

			//絶対行う
			if(stmt != null){
				stmt.close();
			}

		}

	}

}

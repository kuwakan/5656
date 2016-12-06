package casestudy;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

public class ConnectDBTest {

	@Test
	public void DBとの接続クローズを行うテスト() {

		Connection con = null;

		try{
			con = ConnectionManager.getConnection();
			System.out.println("DB接続成功");

		}catch (SQLException e) {
			System.out.println("接続に失敗しました");
			e.printStackTrace();
		}

		try {
			if(con != null){
				con.close();
				System.out.println("DBクローズ成功");
			}
		} catch(SQLException e) {
			System.out.println("クローズに失敗しました");
			e.printStackTrace();
		}

	}

}

package casestudy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	/** データベース接続URL */
	private static final String URL = "jdbc:oracle:thin:@DB00:1521:ascdb";
	/** ユーザー名 */
	private static final String USER = "asc45th";
	/** パスワード */
	private static final String PASSWORD = "system";


	/**
	 * データベースの接続を取得する。
	 * @return データベースの接続
	 */
	public static synchronized Connection getConnection() throws SQLException {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}

		return con;
	}
}

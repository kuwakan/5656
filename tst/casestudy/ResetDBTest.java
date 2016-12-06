package casestudy;

import java.sql.SQLException;

import org.junit.Test;

public class ResetDBTest {

	@Test
	public void DBをリセットするテスト() {

		ResetDB resetDB = new ResetDB();
		try {
			resetDB.resetDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}

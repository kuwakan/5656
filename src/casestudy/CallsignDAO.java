package casestudy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CallsignDAO {
	private  Connection con;

	public CallsignDAO(Connection con) {
		this.con = con;
	}

	public Callsign[] findcall(Position[] posi) throws SQLException {
		String sql = "select * from callsign where timestamp in (select max(timestamp) from callsign group by modes) and modes = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet res = null;
		Callsign call[] = new Callsign[100];


		int i=0;
		try{

			while(posi[i]!=null){
				//stmt = ;
				stmt.setString(1,posi[i].getModes());
				res = stmt.executeQuery();//sqlをとってくる
				System.out.print(posi[i].getModes());
				if(res.next()){
					call[i] = new Callsign(//列の名前を書く.その列のデータをとってきてくれる
							res.getString("modes"),
							res.getString("callsign")
							);
				}
				i++;

			}
		}finally{//絶対行う
			if(res != null){
				res.close();
			}
			if(stmt != null){
				stmt.close();

			}

		}
		return call;
	}


	public DB_Item_CallSign insertcallsign(DB_Item_CallSign callsign) throws SQLException{
		String sql = "INSERT INTO callsign(modes, callsign, timestamp)"+
					"VALUES(?,?,systimestamp)";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1,callsign.getModeSAddress());
			stmt.setString(2,callsign.getCallSign());


			stmt.executeUpdate();//追加するinsert

		} finally {
			if (stmt != null){
				stmt.close();
			}
		}

		return callsign;
	}

	public void resetCallSignDB() throws SQLException {
		Statement stmt = null;

		try{

			stmt = con.createStatement();
			stmt.executeUpdate("TRUNCATE TABLE callsign");


		}catch (SQLException e){

			  System.out.println("SQLException:" + e.getMessage());

		}finally{

			if(stmt != null){
				stmt.close();
			}
			System.out.println("Callsignテーブルリセット完了");

		}


	}

	public void exportCallSignCSV() throws SQLException {
		try {



			DBWriter dbWriter = new DBWriter(con);
			dbWriter.createCSV("callsign");

			System.out.println("エクスポート完了");

		}catch (Exception e) {
			// 何らかのエラーがあっても表示するのみ
			System.out.println("エラーです");
			e.printStackTrace();
		}



	}


}

package casestudy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * POSITIONテーブルのDAO
 */
public class PositionDAO {

	private  Connection con;

	public PositionDAO(Connection con) {
		this.con = con;
	}

	public PlanePositionReadDB[] findposi() throws SQLException {
		String sql = "select * from position where timestamp in (select max(timestamp) from position group by modes)"
				+ "and  timestamp > systimestamp-0.0008";
				//+"and  extract(second from systimestamp)-extract(second from timestamp)  < 10"
				//+"and  extract(minute from timestamp)-extract(minute from systimestamp)=0"
				//+"and  extract(hour from timestamp)-extract(hour from systimestamp)=0";
				//+"and  extract(day from timestamp)=extract(day from sysdate)"
				//+"and  extract(month from timestamp)=extract(month from sysdate)"
				//+"and  extract(year from timestamp) = extract(year from sysdate)";

		PreparedStatement stmt = null;
		ResultSet res = null;
		PlanePositionReadDB posi[] = new PlanePositionReadDB[100];


		try{
			stmt = con.prepareStatement(sql);
			//stmt.setString(1,modes);
			res = stmt.executeQuery();//sqlをとってくる
			int i=0;
			while(res.next()){



				posi[i] = new PlanePositionReadDB(//列の名前を書く.その列のデータをとってきてくれる
						res.getString("modes"),
						res.getFloat("latitude"),
						res.getFloat("longitude"),
						res.getFloat("altitude"),
						res.getTimestamp("timestamp")
						);
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
		return posi;
	}

	/**
	 * DBにデータを格納する
	 * @param position DB_Item_PlanePositionオブジェクト
	 */
	public DB_Item_PlanePosition insertposition(DB_Item_PlanePosition position) throws SQLException{
		String sql = "INSERT INTO position(modeS,  latitude, longitude, altitude, timestamp)"+
					"VALUES(?,?,?,?,systimestamp)";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1,position.getModeSAddress());
			stmt.setDouble(2,position.getPlanePosition().getLat());
			stmt.setDouble(3,position.getPlanePosition().getLon());
			stmt.setDouble(4,position.getPlanePosition().getAlt());

			stmt.executeUpdate();//追加するinsert

		} finally {
			if (stmt != null){
				stmt.close();
			}
		}

		return position;
	}

	public void resetPositionDB()  throws SQLException {
		Statement stmt = null;

		try{

			stmt = con.createStatement();
			stmt.executeUpdate("TRUNCATE TABLE position");


		}catch (SQLException e){

			  System.out.println("SQLException:" + e.getMessage());

		}finally{

			if(stmt != null){
				stmt.close();
			}
			System.out.println("Positionテーブルリセット完了");

		}

	}

	public void exportPositionCSV() throws SQLException {
		try {



			DBWriter dbWriter = new DBWriter(con);
			dbWriter.createCSV("position");

			System.out.println("エクスポート完了");

		}catch (Exception e) {
			// 何らかのエラーがあっても表示するのみ
			System.out.println("エラーです");
			e.printStackTrace();
		}



	}



}

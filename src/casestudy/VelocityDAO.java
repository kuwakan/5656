package casestudy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VelocityDAO {

	private  Connection con;

	public VelocityDAO(Connection con) {
		this.con = con;
	}

	public Velocity2[] findvelo(Position[] posi) throws SQLException {


		String sql = "select * from velocity where timestamp in (select max(timestamp) from velocity group by modes) and modes = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet res = null;
		Velocity2[] velo = new Velocity2[100];

		int i=0;
		try{

			while(posi[i]!=null){

				//stmt =
				stmt.setString(1,posi[i].getModes());
				res = stmt.executeQuery();//sqlをとってくる
				if(res.next()){
					velo[i] = new Velocity2(//列の名前を書く.その列のデータをとってきてくれる
							res.getString("modes"),
							res.getFloat("h_velocity"),
							res.getFloat("v_velocity"),
							res.getFloat("h_direction"),
							res.getFloat("v_direction")
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
		return velo;
	}


	public DB_Item_Velocity insertvelocity(DB_Item_Velocity velocity) throws SQLException{
		String sql = "INSERT INTO velocity(modes, H_velocity, V_velocity, H_direction, V_direction, timestamp)"+
					"VALUES(?,?,?,?,?,systimestamp)";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);

			stmt.setString(1,velocity.getModeSAddress());
			stmt.setDouble(2,velocity.getVelocity().getVel());
			stmt.setDouble(3,velocity.getVelocity().getVr());
			stmt.setInt(4,velocity.getVelocity().getDeg());
			stmt.setInt(5,velocity.getVelocity().getS_Vr());
//			stmt.setLong(6,velocity.getTimeStamp());

			stmt.executeUpdate();//追加するinsert

		} finally {
			if (stmt != null){
				stmt.close();
			}
		}

		return velocity;
	}
}

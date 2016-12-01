package casestudy;
import java.sql.Connection;
import java.sql.SQLException;

public class Testinsert {
	public static void callsigninsert(DB_Item_CallSign callsign) {
		Connection   con = null;


		try{
			con = ConnectionManager.getConnection();
			System.out.println("接続完了call");

			CallsignDAO InsCallsign =new CallsignDAO(con);
			InsCallsign.insertcallsign(callsign);

		}catch (SQLException e) {
			System.out.println("1登録に失敗しました");
			e.printStackTrace();
		}
		try {
			if(con != null){
				con.close();
				System.out.println("クローズ完了");
			}
		} catch(SQLException e) {
				e.printStackTrace();
		}

		System.out.println("以下の内容を登録しました");
		System.out.println(callsign.getModeSAddress());
		System.out.println(callsign.getCallSign());

	}

	public static void positioninsert(DB_Item_PlanePosition position) {
		Connection   con = null;

		try{
			con = ConnectionManager.getConnection();
			System.out.println("接続完了posi");

			PositionDAO InsPosition =new PositionDAO(con);
			InsPosition.insertposition(position);

		}catch (SQLException e) {
			System.out.println("2登録に失敗しました");
			e.printStackTrace();
		}
		try {
			if(con != null){
				con.close();
				System.out.println("クローズ完了");
			}
		} catch(SQLException e) {
				e.printStackTrace();
		}

		System.out.println("以下の内容を登録しました");
		System.out.println(position.getModeSAddress());
		System.out.println(position.getPlanePosition().getLon());
		System.out.println(position.getPlanePosition().getLat());
		System.out.println(position.getPlanePosition().getAlt());
	}

	public static void velocityinsert(DB_Item_Velocity velocity) {
		Connection   con = null;

		try{
			con = ConnectionManager.getConnection();
			System.out.println("接続完了velo");

			VelocityDAO InsVelocity =new VelocityDAO(con);
			InsVelocity.insertvelocity(velocity);

		}catch (SQLException e) {
			System.out.println("3登録に失敗しました");
			e.printStackTrace();
		}
		try {
			if(con != null){
				con.close();
				System.out.println("クローズ完了");
			}
		} catch(SQLException e) {
				e.printStackTrace();
		}

		System.out.println("以下の内容を登録しました");
		System.out.println(velocity.getModeSAddress());
		System.out.println(velocity.getVelocity().getS_Vr());
		System.out.println(velocity.getVelocity().getVr());
		System.out.println(velocity.getVelocity().getDeg());
		System.out.println(velocity.getVelocity().getVel());

	}
}

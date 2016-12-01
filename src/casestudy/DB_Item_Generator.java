package casestudy;

public class DB_Item_Generator {

	public static void dB_Item_CallSign_Generate(String modeS, String callSign){
		DB_Item_CallSign db_Item_CallSign = new DB_Item_CallSign(modeS, callSign);
		Testinsert.callsigninsert(db_Item_CallSign);//DBにCallsignを格納
		//System.out.println(db_Item_CallSign.toString());

	}

	public static void dB_Item_Velocity_Generate(String modeS, Velocity velocity){
		DB_Item_Velocity db_Item_Velocity = new DB_Item_Velocity(modeS, velocity);

		Testinsert.velocityinsert(db_Item_Velocity);//DBにVelocityを格納
		//System.out.println(db_Item_Velocity.toString());

	}

	public static void dB_Item_PlanePosition_Generate(String modeS, PlanePosition planePosition){
		DB_Item_PlanePosition db_Item_PlanePosition = new DB_Item_PlanePosition(modeS, planePosition);

		Testinsert.positioninsert(db_Item_PlanePosition);//DBにPlanepositionを格納
		//System.out.println(db_Item_PlanePosition.toString());

	}

}

package casestudy;

/**
 * DB_Itemを作成するクラス
 */
public class DB_Item_Generator {

	/**
	 * コンストラクタ
	 * @param modeS ModeSアドレス
	 * @param callSign コールサイン
	 */
	public static void dB_Item_Generate(String modeS, String callSign){
		DB_Item_CallSign db_Item_CallSign = new DB_Item_CallSign(modeS, callSign);

//		//DBにCallsignを格納
		InsertDB.callSignInsert(db_Item_CallSign);

//		System.out.println(db_Item_CallSign.toString());

	}

	/**
	 * コンストラクタ
	 * @param modeS ModeSアドレス
	 * @param velocity 速度情報（Velocityオブジェクト）
	 */
	public static void dB_Item_Generate(String modeS, Velocity velocity){
		DB_Item_Velocity db_Item_Velocity = new DB_Item_Velocity(modeS, velocity);

//		//DBにVelocityを格納
		InsertDB.velocityInsert(db_Item_Velocity);

//		System.out.println(db_Item_Velocity.toString());

	}

	/**
	 * コンストラクタ
	 * @param modeS ModeSアドレス
	 * @param planePosition 位置情報（PlanePositionオブジェクト）
	 */
	public static void dB_Item_Generate(String modeS, PlanePosition planePosition){
		DB_Item_PlanePosition db_Item_PlanePosition = new DB_Item_PlanePosition(modeS, planePosition);

//		//DBにPlanePositionを格納
		InsertDB.positionInsert(db_Item_PlanePosition);

//		System.out.println(db_Item_PlanePosition.toString());

	}

}

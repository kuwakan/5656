package casestudy;

public class RealData extends Thread{
	final static String ipAddress = "192.168.3.171";
	final static int portNum = 10001;

	boolean doin = true;

	public void run(){
		SensorAccessObject testSOA = new SensorAccessObject(ipAddress, portNum);

		/*
		 * SBS-3と接続
		 */
		testSOA.connect();

		/*
		 * 500行分のデータを受信、解析する
		 */


		try{



			while(doin){
				String hex = testSOA.readSensor();
				EvenAndOddMatcher.analyzeData(hex);
			}
			//kurachan.run();


		}finally{

			/*
			 * SBS-3との接続を切断
			 */
			testSOA.close();
			System.out.println("*** 終了 ***");
		}


	}

}

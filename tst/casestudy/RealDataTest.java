package casestudy;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RealDataTest {

	final static String ipAddress = "192.168.3.171";
	final static int portNum = 10001;


	@Category(SlowTests.class)
	@Test
	public void SBS3を使用したテスト() {

		SensorAccessObject testSOA = new SensorAccessObject(ipAddress, portNum);

		/*
		 * SBS-3と接続
		 */
		testSOA.connect();

		/*
		 * 500行分のデータを受信、解析する
		 */

		try{
			for(int i = 0; i < 500;i++ ){
				String hex = testSOA.readSensor();
				EvenAndOddMatcher.analyzeData(hex);
			}
		}finally{

			/*
			 * SBS-3との接続を切断
			 */
			testSOA.close();
			System.out.println("*** 終了 ***");
		}

	}

}

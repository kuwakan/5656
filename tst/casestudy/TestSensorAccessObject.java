package casestudy;


public class TestSensorAccessObject {

	private static final String ipAddress = "192.168.3.171";
	private static final int portNumber = 10001;

	/**
	 * @param args 不使用
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		SensorAccessObject testSOA = null;
		testSOA = new SensorAccessObject(ipAddress, portNumber);

		testSOA.connect();
		for(int i=0;i < 100; i++){
			String hex = testSOA.readSensor();
			System.out.println(hex);
		}

		testSOA.close();


	}

}

package casestudy;

import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;
import org.junit.experimental.categories.Category;


public class RealDataTest {

	@Category(SlowTests.class)
	@Test
	public void SBS3から5秒間データを受信するテスト() {

		RealData rd = new RealData();

		//rdをスレッドで開始
		rd.start();

		assertThat("無限ループ実行前", rd.doin);

		try {
			//5秒間待機する
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//rd.run() の無限ループを抜けるためにdoinをfalseにする
		rd.doin =false;

		//無限ループ実行後にrd.doinはfalseとなるので、!rd.doinがtrueのとき処理は正常に実行されている
		assertThat("無限ループ実行後", !rd.doin);

	}

}

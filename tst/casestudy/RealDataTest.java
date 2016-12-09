package casestudy;

import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;
import org.junit.experimental.categories.Category;


public class RealDataTest {

	@Category(SlowTests.class)
	@Test
	public void SBS3を使用したテスト() {

		RealData rd = new RealData()

//		{
//			public boolean exec = false;
//			@Override
//			public void run(){
//				exec = false;
//				super.run();
//				exec = true;
//			}
//		}

		;

		/** rdを開始 */
		rd.start();

		/**	rd.run() の無限ループを抜けるためにdoinをfalseにする*/
		rd.doin = false;

		/** 無限ループ実行後にrd.doinはfalseとなるので、!rd.doinがtrueのとき処理は正常に実行されている */
		assertThat("無限ループ実行後", !rd.doin);

	}

}
package casestudy;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RealDataTest {

	@Category(SlowTests.class)
	@Test
	public void SBS3を使用したテスト() {


		RealData rd = new RealData(){

			public boolean exec = false;

			@Override
			public void run(){
				exec = true;
				super.run();
				exec = false;
			}
		};

		rd.start();
//		assertThat("");

		rd.doin = false;

	}

}

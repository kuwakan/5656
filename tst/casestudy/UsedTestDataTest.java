package casestudy;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class UsedTestDataTest {

	@Category(SlowTests.class)
	@Test
	public void テストデータを使用したテスト() {
		/*
		 * 867行分のデータを受信、解析する
		 */
		for(int i = 1;i < 867; i++){
			String hex = TestDataRead.fileReadLine(i);
			EvenAndOddMatcher.analyzeData(hex);

		}
		System.out.println("終了しました");
	}
}

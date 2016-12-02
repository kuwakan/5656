package casestudy;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

public class EvenAndOddMatcherTest {

	@Test
	public void test仮おき() {

		String actual = EvenAndOddMatcher.analyzeData(TestDataRead.fileReadLine(22));
		String expected = null;

		assertThat(actual,is(expected));

	}

}

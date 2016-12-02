package casestudy;

import static casestudy.CallSignFactory.*;
import static casestudy.TestDataRead.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class CallSignFactoryTest {

	@Test
	public void テストデータ130行目によりコールサインJAL2464_が返るテスト() {
		String expected 	= "JAL2464_";
		String actual		= calcCallSign(fileReadLineBinary(130));

		assertThat(actual, is(expected));
	}

	@Test
	public void テストデータ147行目によりコールサインANA764__が返るテスト() {
		String expected 	= "ANA764__";
		String actual		= calcCallSign(fileReadLineBinary(147));

		assertThat(actual, is(expected));
	}

}

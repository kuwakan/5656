package casestudy;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

public class HexToBinaryTest {

	@Test
	public void Hex形式のデータの1行目をバイナリ形式に変更するtest() {
		String actual = HexToBinary.hexToBinary(TestDataRead.fileReadLine(1));
		String expected ="000100000000001000000111000000000001010010111110010000010010000000000000000010110010110010000110001111011010010100010000000000110101110100100101";
		assertThat(actual,is(expected));
	}


	@Test
	public void Hex形式のデータの200行目をバイナリ形式に変更する行目test() {
		String actual = HexToBinary.hexToBinary(TestDataRead.fileReadLine(200));
		String expected ="00010000000000100000000100000000100100010101001100110001100100001000011110100000110111011100000100000000000000000000000000000000001001000000010000000000000000000000000000010000000000110011000100110011";
		assertThat(actual,is(expected));
	}

}

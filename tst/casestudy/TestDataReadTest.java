package casestudy;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

public class TestDataReadTest {

	@Test
	public void テストデータからデータを読めるかテスト0行目() {
		String actual = TestDataRead.fileReadLine("test1000.txt", 0);
		String expected = "";
		assertThat(actual,is(expected));
	}

	@Test
	public void テストデータからデータを読めるかテスト1行目() {
		String actual = TestDataRead.fileReadLine("test1000.txt", 1);
		String expected = "10 02 07 00 14 be 41 20 00 0b 2c 86 3d a5 10 03 5d 25 ";
		assertThat(actual,is(expected));
	}

	@Test
	public void テストデータからデータを読めるかテスト20行目() {
		String actual = TestDataRead.fileReadLine("test1000.txt", 20);
		String expected = "10 02 07 00 60 37 31 20 20 06 93 cf 65 ef 10 03 3e 1a ";
		assertThat(actual,is(expected));
	}


	@Test
	public void テストデータからデータを読めるかテスト868行目最終行() {
		String actual = TestDataRead.fileReadLine("test1000.txt", 868);
		String expected = "";
		assertThat(actual,is(expected));
	}

	@Test
	public void テストデータからデータを読めるかテスト869行目最終行の次() {
		String actual = TestDataRead.fileReadLine("test1000.txt", 869);
		String expected = "";
		assertThat(actual,is(expected));
	}

}

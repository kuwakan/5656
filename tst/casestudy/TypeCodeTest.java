package casestudy;

import static casestudy.TypeCode.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;


public class TypeCodeTest {


	@Test
	public void createVerocityTestテストデータ4行目_12行目_13行目() {
		TypeCode actual = createTypeCode((TestDataRead.fileReadLineBinary(4)));
		TypeCode expected = VELOCITY;
		assertThat("テストデータ4行目:",actual,is(expected));

		actual = createTypeCode((TestDataRead.fileReadLineBinary(12)));
		assertThat("テストデータ12行目:",actual,is(expected));

		actual = createTypeCode((TestDataRead.fileReadLineBinary(13)));
		assertThat("テストデータ132行目:",actual,is(expected));
	}
	@Test
	public void createCallSignTestテストデータ130行目_147行目_189行目() {
		TypeCode actual = createTypeCode((TestDataRead.fileReadLineBinary(130)));
		TypeCode expected = CALL_SIGN;
		assertThat("テストデータ130行目:",actual,is(expected));

		actual = createTypeCode((TestDataRead.fileReadLineBinary(147)));
		assertThat("テストデータ147行目:",actual,is(expected));

		actual = createTypeCode((TestDataRead.fileReadLineBinary(189)));
		assertThat("テストデータ189行目:",actual,is(expected));
	}
	@Test
	public void createPlanePositonTestテストデータ11行目_32行目_61行目() {
		TypeCode actual = createTypeCode((TestDataRead.fileReadLineBinary(11)));
		TypeCode expected = PLANE_POSITION;
		assertThat("テストデータ11行目:",actual,is(expected));

		actual = createTypeCode((TestDataRead.fileReadLineBinary(32)));
		assertThat("テストデータ32行目:",actual,is(expected));

		actual = createTypeCode((TestDataRead.fileReadLineBinary(61)));
		assertThat("テストデータ61行目:",actual,is(expected));
	}
	@Test
	public void createOtherTestテストデータ3行目_5行目_7行目() {
		TypeCode actual = createTypeCode((TestDataRead.fileReadLineBinary(3)));
		TypeCode expected = OTHER;
		assertThat("テストデータ3行目:",actual,is(expected));

		actual = createTypeCode((TestDataRead.fileReadLineBinary(5)));
		assertThat("テストデータ5行目:",actual,is(expected));

		actual = createTypeCode((TestDataRead.fileReadLineBinary(7)));
		assertThat("テストデータ7行目:",actual,is(expected));
	}

}

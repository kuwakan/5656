package casestudy;

import static casestudy.PlanePositionFactory.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class PlanePositionFactoryTest {

	public static class PlanePosition作成テスト{
		@Test
		public void テストデータ11行目と32行目からPlanePosition作成テスト() {
			double actual ;
			double expected = 36.077286856515066;
			PlanePosition sut;

			rawDataToPlanePosition(TestDataRead.fileReadLineBinary(11));
			sut = rawDataToPlanePosition(TestDataRead.fileReadLineBinary(32));
			actual = sut.getLat();
			assertThat("LAT TEST:",actual, is(expected));

			expected = 138.14062889586106;
			actual = sut.getLon();
			assertThat("LON TEST:",actual, is(expected));

			expected = 13200.0;
			actual = sut.getAlt();
			assertThat("ALT TEST:",actual, is(expected));

		}

		@Test
		public void テストデータ32行目と61行目からPlanePosition作成テスト() {
			double actual ;
			double expected = 34.24406433105469;
			PlanePosition sut;

			rawDataToPlanePosition(TestDataRead.fileReadLineBinary(32));
			sut = rawDataToPlanePosition(TestDataRead.fileReadLineBinary(61));
			actual = sut.getLat();
			assertThat("LAT TEST:",actual, is(expected));

			expected = 135.26372792769453;
			actual = sut.getLon();
			assertThat("LON TEST:",actual, is(expected));

			expected = 13200.0;
			actual = sut.getAlt();
			assertThat("ALT TEST:",actual, is(expected));

		}
	}

	public static class Listへの追加テスト{
		@Test
		public void OddListへ追加するテスト(){
			int expected = getOddDataList().size() + 1;
			int actual = listAdd(TestDataRead.fileReadLineBinary(328),getOddDataList());
			assertThat("ODD:",actual, is(expected));
		}

		@Test
		public void EvenListへ追加するテスト(){
			int expected = getEvenDataList().size()  + 1;
			int actual = listAdd(TestDataRead.fileReadLineBinary(304),getEvenDataList());
			assertThat("EVEN:",actual, is(expected));
		}

	}

}

package casestudy;

import static casestudy.LatLonAltAnalyzer.*;
import static casestudy.TestDataRead.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

public class LatLonAltAnalyzerTest {
	@Test
	public void nlTest34_6で49を返す(){
		int actual	= nl(34.6);
		int expected	= 49;
		assertThat(actual, is(expected));
	}
	@Test
	public void binToLonCPRFormatTestテストデータ11行目で0_410622を返す(){
		double actual	= lon_cpr(fileReadLineBinary(11));
		double expected	= 0.41062164306640625;
		assertThat(actual, is(expected));
	}

	@Test
	public void binToLonCPRFormatTestテストデータ32行目で0_035027を返す(){
		double actual	= lon_cpr(fileReadLineBinary(32));
		double expected	= 0.03502655029296875;
		assertThat(actual, is(expected));
	}
	@Test
	public void latETestテストデータ844行目548行目のEvenで49を返す(){
		double actual		= latE(fileReadLineBinary(844),fileReadLineBinary(548));
		double expected	= 34.617919921875;
		assertThat(actual, is(expected));
	}

	@Test
	public void countOfLatZoneTestテストデータ844行目548行目のEvenで49を返す(){
		int actual		= nl(latE(fileReadLineBinary(844),fileReadLineBinary(548)));
		int expected	= 49;
		assertThat(actual, is(expected));
	}

	@Test
	public void countOfLatZoneTestテストデータ11行目32行目のEvenで49を返す(){
		int actual		= nl(latE(fileReadLineBinary(11),fileReadLineBinary(32)));
		int expected	= 49;
		assertThat(actual, is(expected));
	}
	@Test
	public void countOfLatZoneTestテストデータ11行目32行目のOddで49を返す(){
		int actual		= nl(latO(fileReadLineBinary(11),fileReadLineBinary(32)));
		int expected	= 49;
		assertThat(actual, is(expected));
	}

	@Test
	public void latIndexJTestテストデータ11_32行目で5を返す(){
		int actual		= (int)(latIndexJ(fileReadLineBinary(11), fileReadLineBinary(32)));
		int expected	= 5;
		assertThat(actual, is(expected));
	}

	@Test
	public void binToLatCPRFormatTestテストデータ11行目で0_706932を返す(){
		double actual	= lat_cpr(fileReadLineBinary(11));
		double expected	= 0.7069320678710938;
		assertThat(actual, is(expected));
	}

	@Test
	public void binToLatCPRFormatTestテストデータ32行目で0_612022を返す(){
		double actual	= lat_cpr(fileReadLineBinary(32));
		double expected	= 0.6120223999023438;
		assertThat(actual, is(expected));
	}

	@Test
	public void calcLatOTestテストデータ11_32行目で34_24285を返す(){
		double actual	= latO(fileReadLineBinary(11),fileReadLineBinary(32));
		double expected	= 34.24284854177701;
		assertThat(actual, is(expected));
	}

	@Test
	public void calcLatETestテストデータ11_32行目で34_24285を返す(){
		double actual	= latE(fileReadLineBinary(11),fileReadLineBinary(32));
		double expected	= 34.24159240722656;
		assertThat(actual, is(expected));
	}


	@Test
	public void countOfLatZoneTest0度で59個を返す() {
		int actual		= nl(0);
		int expected	= 59;
		assertThat(actual, is(expected));
	}
	@Test
	public void countOfLatZoneTest86_5度で3個を返す() {
		int actual		= nl(86.5);
		int expected	= 3;
		assertThat(actual, is(expected));
	}
	@Test
	public void countOfLatZoneTest86度で3個を返す() {
		int actual		= nl(86);
		int expected	= 3;
		assertThat(actual, is(expected));
	}
	@Test
	public void countOfLatZoneTest87度で2個を返す() {
		int actual		= nl(87);
		int expected	= 2;
		assertThat(actual, is(expected));
	}
	@Test
	public void countOfLatZoneTest88度で1個を返す() {
		int actual		= nl(88);
		int expected	= 1;
		assertThat(actual, is(expected));
	}
	@Test
	public void countOfLatZoneTestマイナス86_6度で2個を返す() {
		int actual		= nl(-86.6);
		int expected	= 2;
		assertThat(actual, is(expected));
	}
	@Test
	public void countOfLatZoneTestマイナス89度で1個を返す() {
		int actual		= nl(-89);
		int expected	= 1;
		assertThat(actual, is(expected));
	}

	@Test
	public void テストデータ528行目629行目calcLat_calcLon_calcAlt() {
		String dataE = fileReadLineBinary(528);
		String dataO = fileReadLineBinary(629);
		boolean evenNewThanOdd = false;
		double actual	= calcLat(dataE,dataO,evenNewThanOdd);
		double expected	= 34.78667178396451;
		assertThat("Lat:",actual, is(expected));
		actual	= calcLon(dataE,dataO,evenNewThanOdd);
		expected	= 133.6765480041504;
		assertThat("Lon:",actual, is(expected));
		int actual2	= calcAlt(dataE,dataO,evenNewThanOdd);
		int expected2	= 38100;
		assertThat("Alt:",actual2, is(expected2));

	}
	@Test
	public void テストデータ11行目32行目calcLon() {
		String dataE = fileReadLineBinary(11);
		String dataO = fileReadLineBinary(32);
		boolean evenNewThanOdd = false;
		double actual	= calcLon(dataE,dataO,evenNewThanOdd);
		double expected	= 135.26269912719727;
		assertThat("Lon:",actual, is(expected));
	}
	@Test
	public void テストデータ32行目11行目calcLon() {
		String dataE = fileReadLineBinary(11);
		String dataO = fileReadLineBinary(32);
		boolean evenNewThanOdd = true;
		double actual	= calcLon(dataE,dataO,evenNewThanOdd);
		double expected	= 135.26171003069197;
		assertThat("Lon:",actual, is(expected));
	}

	@Test
	public void calcLat_calcLon_calcAltTestテストデータ820行目789行目() {
		String dataE = fileReadLineBinary(820);
		String dataO = fileReadLineBinary(789);
		boolean evenNewThanOdd = true;
		double actual	= calcLat(dataE,dataO,evenNewThanOdd);
		double expected	= 34.782257080078125;
		assertThat("Lat:",actual, is(expected));
		actual	= calcLon(dataE,dataO,evenNewThanOdd);
		expected	= 133.65585638552295;
		assertThat("Lon:",actual, is(expected));
		int actual2	= calcAlt(dataE,dataO,evenNewThanOdd);
		int expected2	= 38100;
		assertThat("Alt:",actual2, is(expected2));
	}
	@Test
	public void calcLat_calcLon_calcAltTestテストデータ789行目528行目() {
		String dataE = fileReadLineBinary(528);
		String dataO = fileReadLineBinary(789);
		boolean evenNewThanOdd = false;
		double actual	= calcLat(dataE,dataO,evenNewThanOdd);
		double expected	= 34.78271484375;
		assertThat("Lat:",actual, is(expected));
		actual	= calcLon(dataE,dataO,evenNewThanOdd);
		expected	= 133.65800857543945;
		assertThat("Lon:",actual, is(expected));
		int actual2	= calcAlt(dataE,dataO,evenNewThanOdd);
		int expected2	= 38075;
		assertThat("Alt:",actual2, is(expected2));
	}
	@Test
	public void calcAltTestテストデータ313行目76行目() {
		String dataE = fileReadLineBinary(528);
		String dataO = fileReadLineBinary(789);
		boolean evenNewThanOdd = false;
		double actual	= calcLat(dataE,dataO,evenNewThanOdd);
		double expected	= 34.78271484375;
		assertThat("Lat:",actual, is(expected));
		actual	= calcLon(dataE,dataO,evenNewThanOdd);
		expected	= 133.65800857543945;
		assertThat("Lon:",actual, is(expected));
		int actual2	= calcAlt(dataE,dataO,evenNewThanOdd);
		int expected2	= 38075;
		assertThat("Alt:",actual2, is(expected2));
	}

}

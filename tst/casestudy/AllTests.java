package casestudy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ LatLonAltAnalyzerTest.class, CallSignFactoryTest.class, EvenAndOddMatcherTest.class,
		HexToBinaryTest.class, PlanePositionFactoryTest.class, RealDataTest.class, TestDataReadTest.class,
		TypeCodeTest.class, VelocityFactoryTest.class, RealDataTest.class, UsedTestDataTest.class })

public class AllTests {

}

package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;
import test.subtester.Game30x30Tester;
import test.subtester.Game5x5Tester;
import test.subtester.Game9x9Tester;

/**
 * Main of the unit test, you can run this class to run all of unit-test that available
 *
 * @author kamontat
 * @version 1.0
 * @since Sat 25/Feb/2017 - 6:00 PM
 */
public class Tester {
	/**
	 * run time: 6 ~ 7 second
	 */
	@RunWith(Suite.class)
	@Suite.SuiteClasses({Game30x30Tester.class, Game9x9Tester.class, Game5x5Tester.class})
	public class AllTests {
		
	}
	
	/**
	 * run all test case:
	 * <ul>
	 * <li>{@link Game5x5Tester} - test board size: 5x5.</li>
	 * <li>{@link Game9x9Tester} - test board size: 9x9.</li>
	 * <li>{@link Game30x30Tester} - test board size: 30x30.</li>
	 * </ul>
	 *
	 * @param args
	 * 		no used
	 */
	public static void main(String[] args) {
		System.out.println();
		Result result = JUnitCore.runClasses(AllTests.class);
		for (Failure failure : result.getFailures()) {
			System.err.println(failure.toString());
		}
		
		String time = "";
		long millis = result.getRunTime();
		long sec = millis / 1000;
		if (sec > 0) {
			millis = millis % 1000;
			time = String.format("%d.%d second", sec, millis);
		} else {
			time = String.format("%s millisecond", millis);
		}
		
		System.out.printf("The Result: \nrunning %d tests in %s \n%s", result.getRunCount(), time, result.wasSuccessful() ? "Complete": "Failure");
	}
}

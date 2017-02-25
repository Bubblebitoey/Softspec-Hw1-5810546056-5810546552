package test;

import java.io.*;

/**
 * Generate the random number: <br>
 * This class have 2 return type to use.
 * <ol>
 * <li>return as array of integer.</li>
 * <li>return as input stream (you can use it to Game class).</li>
 * </ol>
 *
 * @author kamontat
 * @version 1.0
 * @since Sat 25/Feb/2017 - 2:59 AM
 */
public class Randoms {
	private static final File tempFolder = new File("src/test/testfile/temp");
	/**
	 * temp file that generating, inside will content all random number.
	 */
	private static final File file = new File(tempFolder, "gens_random_numbers.txt");
	
	/**
	 * write the random integer array into file.
	 *
	 * @param randoms
	 * 		the random numbers array.
	 * @throws FileNotFoundException
	 * 		when program can't create the file.
	 */
	private static void writeToFile(int[] randoms) throws FileNotFoundException {
		if (tempFolder.mkdir()) {
			System.out.println("create temp file at (" + tempFolder.getPath() + ")");
		}
		PrintWriter printer = new PrintWriter(new FileOutputStream(file));
		boolean switcher = true;
		for (int input : randoms) {
			if (switcher) {
				printer.print(input + " ");
				switcher = false;
			} else {
				printer.println(input + " ");
				switcher = true;
			}
		}
		printer.close();
	}
	
	/**
	 * get array that contains of all random number with length {@code size} and all number is in length (1to max) both inclusive.
	 *
	 * @param size
	 * 		the array size
	 * @param max
	 * 		the maximum number (inclusive)
	 * @return the randoms array.
	 */
	public static int[] getRandomIntArray(int size, int max) {
		int[] nums = new int[size];
		for (int i = 0; i < size; i++) {
			nums[i] = (int) Math.ceil(Math.random() * max);
		}
		return nums;
	}
	
	/**
	 * get random number {@code size} size with in length (1 to max) both inclusive.
	 *
	 * @param size
	 * 		the input size
	 * @param max
	 * 		the maximum of number (inclusive) inside input stream
	 * @return input stream of randoms
	 * @see BufferedReader
	 * @see java.util.Scanner
	 */
	public static InputStream getRandomInputStream(int size, int max) {
		try {
			writeToFile(getRandomIntArray(size, max));
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			return System.in;
		}
	}
	
	/**
	 * remove the generate randoms file
	 */
	public static void removeTempFile() {
		file.delete();
	}
}

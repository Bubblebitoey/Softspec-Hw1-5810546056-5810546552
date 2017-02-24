package test;

/**
 * @author kamontat
 * @version 1.0
 * @since Sat 25/Feb/2017 - 2:59 AM
 */
public class GenNumber {
	public static int[] gen(int number, int max) {
		int[] nums = new int[number];
		for (int i = 0; i < number; i++) {
			nums[i] = (int) Math.ceil(Math.random() * max);
		}
		return nums;
	}
	
	public static void main(String[] args) {
		for (int num : gen(99, 9)) {
			System.out.printf("%s ", num);
		}
	}
}

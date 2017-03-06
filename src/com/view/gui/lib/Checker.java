package com.view.gui.lib;

/**
 * The checker to check string can or can't change to number.
 *
 * @author kamontat
 * @version 1.0
 * @since Mon 06/Mar/2017 - 7:39 PM
 */
public class Checker {
	/**
	 * check input string must contains digit and <b>dot</b> ONLY.
	 *
	 * @param input
	 * 		string to check.
	 * @return true if input is {@link Float} or {@link Double}.
	 */
	public static boolean isAllDecimal(String input) {
		boolean dot = false;
		
		// if input is empty String
		if (input == null || input.length() == 0) return false;
		
		// check every char in input String
		for (char aChar : input.toCharArray()) {
			// check 'dot' first time
			if (aChar == '.' && !dot) dot = true;
				// else have 'dot' more than 1
			else if (aChar == '.') return false;
				// else isn't 'dot'
			else if (!Character.isDigit(aChar)) return false;
		}
		return true;
	}
	
	/**
	 * check input string must contains digit ONLY.
	 *
	 * @param input
	 * 		string to check.
	 * @return true if input is {@link Integer} or {@link Long}.
	 */
	public static boolean isAllNumber(String input) {
		// if input is empty String
		if (input == null || input.length() == 0) return false;
		
		// check every char in input String
		for (char aChar : input.toCharArray()) {
			if (!Character.isDigit(aChar)) return false;
		}
		return true;
	}
}

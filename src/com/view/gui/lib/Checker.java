package com.view.gui.lib;

/**
 * The checker to check string parse to integer
 *
 * @author kamontat
 * @version 1.0
 * @since Mon 06/Mar/2017 - 7:39 PM
 */
public class Checker {
	/**
	 * check include dot
	 *
	 * @param input
	 * @return
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
	 * check exclude dot
	 *
	 * @param input
	 * @return
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
	
	/**
	 * parse input to aClass <br>
	 * Example: <br>
	 * <code>parse("12.34", Double.class);</code> <br>
	 * <code>parse("2", Integer.class);</code>
	 *
	 * @param input
	 * 		input to parse
	 * @param aClass
	 * 		cannot be interface or anonymous class
	 * @param <T>
	 * 		expected output class
	 * @return number of input in <code>{@link Class}</code> class, or null if cannot parse to <code>Class</code>
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Number> T parse(String input, Class<T> aClass) {
		if (aClass.isInterface() || aClass.isAnonymousClass()) return null;
		try {
			if (aClass.getName().equals(Byte.class.getName())) {
				return aClass.cast(new Byte(input));
			} else if (aClass.getName().equals(Double.class.getName())) {
				return aClass.cast(new Double(input));
			} else if (aClass.getName().equals(Float.class.getName())) {
				return aClass.cast(new Float(input));
			} else if (aClass.getName().equals(Integer.class.getName())) {
				return aClass.cast(new Integer(input));
			} else if (aClass.getName().equals(Long.class.getName())) {
				return aClass.cast(new Long(input));
			} else if (aClass.getName().equals(Short.class.getName())) {
				return aClass.cast(new Short(input));
			}
		} catch (NumberFormatException e) {
			// System.err.println(e.getMessage());
		}
		return null;
	}
	
	/**
	 * for testing only
	 *
	 * @param args
	 * 		no used
	 */
	public static void main(String[] args) {
		System.out.println(isAllDecimal("120.22"));      // true
		System.out.println(isAllDecimal("120.23.1"));    // false
		System.out.println(isAllDecimal("1214"));        // true
		System.out.println(isAllDecimal("a554.4"));      // false
		System.out.println(isAllDecimal("879.65755"));   // true
		
		System.out.println("-----------------------------------");
		
		System.out.println(isAllNumber("12455"));      // true
		System.out.println(isAllNumber("120a24a"));    // false
		System.out.println(isAllNumber("970766"));     // true
		System.out.println(isAllNumber("12.44"));      // false
		System.out.println(isAllNumber("0999"));       // true
		
		System.out.println("-----------------------------------");
		
		System.out.println(parse("2120e22", Double.class) != null);                 // true
		System.out.println(parse("120.2.2", Double.class) != null);                 // false
		System.out.println(parse("43.22", Double.class) != null);                   // true
		System.out.println(parse("128", Byte.class) != null);                       // false
		System.out.println(parse("584958497.134965866958", Float.class) != null);   // true
		System.out.println(parse("2e10", Integer.class) != null);                   // false
		System.out.println(parse("223410", Integer.class) != null);                 // true
		System.out.println(parse("abcd", Number.class) != null);                    // false
		System.out.println(parse("-32768", Short.class) != null);                   // true
	}
}

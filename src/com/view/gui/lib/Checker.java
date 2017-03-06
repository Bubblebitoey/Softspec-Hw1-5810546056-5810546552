package com.view.gui.lib;

/**
 * @author kamontat
 * @version 1.0
 * @since Mon 06/Mar/2017 - 7:39 PM
 */
public class Checker {
	public static boolean isAllDouble(String input) {
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
	
	public static boolean isAllInteger(String input) {
		// if input is empty String
		if (input == null || input.length() == 0) return false;
		
		// check every char in input String
		for (char aChar : input.toCharArray()) {
			if (!Character.isDigit(aChar)) return false;
		}
		return true;
	}
	
	public static <T extends Number> Number isNumber(String input, Class<T> aClass) {
		try {
			if (aClass.getName().equals(Double.class.getName())) {
				return Double.parseDouble(input);
			}
			return null;
		} catch (ClassCastException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(isAllDouble("120.22"));
		System.out.println(isAllDouble("120.23.1"));
		System.out.println(isAllDouble("1214"));
		System.out.println(isAllDouble("a554.4"));
		System.out.println(isAllDouble("879.65755"));
		
		System.out.println("-----------------------------------");
		
		System.out.println(isAllInteger("12455"));
		System.out.println(isAllInteger("120a24a"));
		System.out.println(isAllInteger("970766"));
		System.out.println(isAllInteger("12.44"));
		System.out.println(isAllInteger("0999"));
		
		System.out.println("-----------------------------------");
		
		System.out.println(isNumber("120.22", Double.class));
		System.out.println(isNumber("128", Byte.class));
		System.out.println(isNumber("584958495.584965866958", Float.class));
		System.out.println(isNumber("2e30", Integer.class));
	}
}

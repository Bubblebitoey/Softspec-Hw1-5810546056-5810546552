package com.view.gui.lib;

import com.sun.istack.internal.Nullable;

/**
 * This class contains 3 useful method: <br>
 * <ol>
 * <li>{@link #parseTo(String, Class)} - parse <code>input</code> to Number in <code>aClass</code> Class.</li>
 * <li>{@link #getError()} - get error message.</li>
 * <li>{@link #isError()} - check is error occurred.</li>
 * </ol>
 *
 * @author kamontat
 * @version 1.0
 * @since Mon 06/Mar/2017 - 11:55 PM
 */
public class Parser {
	private static final String COMPLETE = "No Error";
	private String errorMessage;
	
	/**
	 * to create this class must used this method to get parsable
	 *
	 * @return Parser to use {@link Parser} method
	 */
	public static Parser getParsable() {
		return new Parser();
	}
	
	/**
	 * Using Method {@link #parse(String, Class)} with saving any error to error message so you can get error message in {@link #getError()} method.
	 *
	 * @param input
	 * 		String input to parse
	 * @param tClass
	 * 		class that want to cast to (cannot be interface or anonymous class).
	 * @param <T>
	 * 		expected output class.
	 * @return number of input in <code>{@link Class}</code> class, or null if cannot parse to <code>Class</code>.
	 */
	public <T extends Number> T parseTo(String input, Class<T> tClass) {
		try {
			return parse(input, tClass);
		} catch (Exception e) {
			errorMessage = e.getMessage();
		}
		return null;
	}
	
	/**
	 * check is error occurred.
	 *
	 * @return true if have error occurred; otherwise, false
	 */
	public boolean isError() {
		return !errorMessage.equals(COMPLETE);
	}
	
	/**
	 * given error message if have error or {@link #COMPLETE}="{@value #COMPLETE}".
	 *
	 * @return error message or {@value #COMPLETE}
	 */
	public String getError() {
		return errorMessage;
	}
	
	/**
	 * constructor, just set error to COMPLETE
	 */
	private Parser() {
		this.errorMessage = COMPLETE;
	}
	
	/**
	 * parse input to aClass. <br>
	 * Example: <br>
	 * <code>parse("12.34", Double.class);</code> <br>
	 * <code>parse("2", Integer.class);</code>
	 *
	 * @param input
	 * 		input to parse.
	 * @param aClass
	 * 		class that want to cast to (cannot be interface or anonymous class).
	 * @param <T>
	 * 		expected output class.
	 * @return number of input in <code>{@link Class}</code> class.
	 * @throws NumberFormatException
	 * 		if can't parse input to aClass, input is null or empty.
	 * @throws ClassCastException
	 * 		if can't cast input to aClass, the aClass is interface or anonymous.
	 */
	@SuppressWarnings("unchecked")
	private <T extends Number> T parse(@Nullable String input, Class<T> aClass) throws NumberFormatException, ClassCastException {
		if (input == null || input.equals("")) throw new NumberFormatException("input string is null or empty.");
		if (aClass.isInterface() || aClass.isAnonymousClass())
			throw new ClassCastException("cannot cast to interface or anonymous class.");
		
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
		throw new ClassCastException("The class isn't Number, or Not implement yet.");
	}
}

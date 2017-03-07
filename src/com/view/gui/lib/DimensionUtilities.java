package com.view.gui.lib;

import java.awt.*;

/**
 * utilities with dimension and screen display.
 *
 * @author kamontat
 * @version 1.3
 * @since 1/30/2017 AD - 1:47 PM
 */
public class DimensionUtilities {
	
	/**
	 * Arithmetic operations.
	 */
	public enum Operation {
		ADD, MINUS, MULTIPLY, DIVIDE;
	}
	
	/**
	 * Screen information <br>
	 * - get size <code>screen.getW</code> and <code>screen.getH</code>
	 */
	private static DisplayMode screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
	
	/**
	 * get Screen size
	 *
	 * @return screen size
	 */
	public static Dimension getScreenSize() {
		return new Dimension(screen.getWidth(), screen.getHeight());
	}
	
	/**
	 * test than pageSize is bigger than screen (computer monitor) or not
	 *
	 * @param pageSize
	 * 		page size (if you fixed size)
	 * @return true if bigger than, otherwise will return false
	 */
	public static boolean isBiggerThanScreen(Dimension pageSize) {
		return !(pageSize.getWidth() < screen.getWidth() && pageSize.getHeight() < screen.getHeight());
	}
	
	/**
	 * return the minimum width and height in a and b <br>
	 * example: a = (200, 400), b = (500, 100) <br>
	 * the result will be (500, 200)
	 *
	 * @param a
	 * 		first size
	 * @param b
	 * 		second size
	 * @return the minimum size
	 */
	public static Dimension maximum(Dimension a, Dimension b) {
		Dimension result = new Dimension();
		if (a.getWidth() > b.getWidth()) result.setSize(a.getWidth(), result.getHeight());
		else result.setSize(b.getWidth(), result.getHeight());
		if (a.getHeight() > b.getHeight()) result.setSize(result.getWidth(), a.getHeight());
		else result.setSize(result.getWidth(), b.getHeight());
		return result;
	}
	
	/**
	 * a [{@link Operation}] b = return result.
	 *
	 * @param a
	 * 		is first dimension.
	 * @param b
	 * 		is second dimension.
	 * @param op
	 * 		is arithmetic Operation.
	 * @return result of 2 <code>a</code> and <code>b</code> do {@link Operation}.
	 */
	public static Dimension operation(Dimension a, Dimension b, Operation op) {
		Dimension result = new Dimension();
		switch (op) {
			case ADD:
				result.height = a.height + b.height;
				result.width = a.width + b.width;
				break;
			case MINUS:
				result.height = a.height - b.height;
				result.width = a.width - b.width;
				break;
			case MULTIPLY:
				result.height = a.height * b.height;
				result.width = a.width * b.width;
				break;
			case DIVIDE:
				result.height = a.height / b.height;
				result.width = a.width / b.width;
				break;
		}
		return result;
	}
}
package com.model.board.shape;

import java.awt.*;

/**
 * This class is for creating board size. <br>
 * Here We implement 2 sub class that you can use to create the board. <br>
 * <ol>
 * <li>{@link Square} - This class will create the square which is both row and column are equal.</li>
 * <li>{@link Rectangle} - This class will create the rectangle size which is row and column not necessary to be equal.</li>
 * </ol>
 * <p>
 * Important: The Default of Row and Column, you can find in {@link #DEFAULT_HEIGHT} and {@link #DEFAULT_WIDTH}.
 *
 * @author kamontat
 * @version 1.1
 * @since Fri 24/Feb/2017 - 11:56 PM
 */
public abstract class Shape extends Dimension {
	private static final int DEFAULT_HEIGHT = 9;
	private static final int DEFAULT_WIDTH = 9;
	
	/**
	 * create the shape with any size by parameters.
	 *
	 * @param width
	 * 		The <b>column</b> or <b>width</b> or <b>x-axis</b>.
	 * @param height
	 * 		The <b>row</b> or <b>height</b> or <b>y-axis</b>.
	 */
	protected Shape(int width, int height) throws NegativeShapeException {
		super(width, height);
		checkValid(this);
	}
	
	/**
	 * create shape with default size ({@link #DEFAULT_WIDTH row/height}, {@link #DEFAULT_HEIGHT column/width}).
	 */
	Shape() {
		super(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	public static Shape checkValid(Shape shape) throws NegativeShapeException {
		if (shape.height > 0 && shape.width > 0) return shape;
		throw new NegativeShapeException();
	}
	
	public static class NegativeShapeException extends Exception {
		public NegativeShapeException() {
			super("size can't be negative number.");
		}
	}
}

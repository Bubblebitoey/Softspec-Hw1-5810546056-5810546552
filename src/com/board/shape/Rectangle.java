package com.board.shape;

/**
 * The Board Size with rectangle shape (row and column are <b>NOT</b> necessary to equals). <br>
 * To use this class, you must use by call {@link #getSize(int, int)} or {@link #getDefaultSize()}.
 *
 * @author kamontat
 * @version 1.0
 * @since Fri 24/Feb/2017 - 11:57 PM
 */
public class Rectangle extends Shape {
	/**
	 * The private constructor that call {@link Shape#Shape(int, int) Shape(row, col)} constructor.
	 *
	 * @param row
	 * 		row(x-axis) of the rectangle.
	 * @param column
	 * 		column(y-axis) of the rectangle.
	 */
	private Rectangle(int row, int column) {
		super(row, column);
	}
	
	/**
	 * The private constructor that call {@link Shape#Shape()} constructor. <br>
	 * this will create will create with {@link #DEFAULT_ROW} and {@link #DEFAULT_COLUMN}.
	 */
	private Rectangle() {
		super();
	}
	
	/**
	 * get Rectangle with any size by parameters.
	 *
	 * @param row
	 * 		row(x-axis) of the rectangle.
	 * @param column
	 * 		column(y-axis) of the rectangle.
	 * @return square
	 */
	public static Rectangle getSize(int row, int column) {
		return new Rectangle(row, column);
	}
	
	/**
	 * use:
	 * <ul>
	 * <li>{@link #DEFAULT_ROW default_row}={@value DEFAULT_ROW}</li>
	 * <li>{@link #DEFAULT_COLUMN default_column}={@value DEFAULT_COLUMN}</li>
	 * </ul>
	 * To create the rectangle.
	 *
	 * @return Rectangle with default size.
	 */
	public static Rectangle getDefaultSize() {
		return new Rectangle();
	}
}

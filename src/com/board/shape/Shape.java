package com.board.shape;

/**
 * This class is for creating board size. <br>
 * Here We implement 2 sub class that you can use to create the board. <br>
 * <ol>
 * <li>{@link Square} - This class will create the square which is both row and column are equal.</li>
 * <li>{@link Rectangle} - This class will create the rectangle size which is row and column not necessary to be equal.</li>
 * </ol>
 * <p>
 * Important: The Default of Row and Column, you can find in {@link #DEFAULT_ROW row} and {@link #DEFAULT_COLUMN column}.
 *
 * @author kamontat
 * @version 1.1
 * @since Fri 24/Feb/2017 - 11:56 PM
 */
public abstract class Shape {
	private static final int DEFAULT_ROW = 9;
	private static final int DEFAULT_COLUMN = 9;
	
	private final int row;
	private final int column;
	
	/**
	 * create the shape with any size by parameters.
	 *
	 * @param row
	 * 		The <b>row</b> or <b>width</b> or <b>y-axis</b>.
	 * @param column
	 * 		The <b>column</b> or <b>height</b> or <b>x-axis</b>.
	 */
	protected Shape(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	/**
	 * create shape with default size ({@link #DEFAULT_ROW row}, {@link #DEFAULT_COLUMN column}).
	 */
	Shape() {
		this.row = DEFAULT_ROW;
		this.column = DEFAULT_COLUMN;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
}

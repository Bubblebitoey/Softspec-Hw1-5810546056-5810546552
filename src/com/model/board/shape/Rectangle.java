package com.model.board.shape;

/**
 * The Board Size with rectangle shape (row and column are <b>NOT</b> necessary to equal). <br>
 * To use this class, you must use by call {@link #getSize(int, int)} or {@link #getDefaultSize()}.
 *
 * @author kamontat
 * @version 1.1
 * @since Fri 24/Feb/2017 - 11:57 PM
 */
public class Rectangle extends Shape {
	/**
	 * The private constructor that call {@link Shape#Shape(int, int) Shape(row, col)} constructor.
	 *
	 * @param row
	 * 		row(y-axis) of the rectangle.
	 * @param column
	 * 		column(x-axis) of the rectangle.
	 * @throws com.model.board.shape.Shape.NegativeShapeException
	 * 		if input negative number.
	 */
	private Rectangle(int row, int column) throws NegativeShapeException {
		super(column, row);
	}
	
	/**
	 * The private constructor that call {@link Shape#Shape()} constructor. <br>
	 * This will create with {@link Shape#Shape()}
	 */
	private Rectangle() {
		super();
	}
	
	/**
	 * get Rectangle with any size by parameters.
	 *
	 * @param row
	 * 		row(y-axis) of the rectangle.
	 * @param column
	 * 		column(x-axis) of the rectangle.
	 * @return square
	 * @throws com.model.board.shape.Shape.NegativeShapeException
	 * 		if input negative number.
	 */
	public static Rectangle getSize(int row, int column) throws NegativeShapeException {
		return new Rectangle(row, column);
	}
	
	/**
	 * this will create with {@link Shape#Shape()}
	 *
	 * @return Rectangle with default size.
	 */
	public static Rectangle getDefaultSize() {
		return new Rectangle();
	}
}

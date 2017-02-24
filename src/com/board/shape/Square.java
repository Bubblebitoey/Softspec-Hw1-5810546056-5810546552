package com.board.shape;

/**
 * The Board Size with square shape (Both row and column are equals). <br>
 * To use this class you must use the method call {@link #getSize(int)} or {@link #getDefaultSize()}.
 *
 * @author bubblebitoey
 * @since 2/23/2017 AD.
 */
public class Square extends Shape {
	/**
	 * The private constructor that call {@link Shape#Shape(int, int) Shape(size, size)} constructor.
	 *
	 * @param size
	 * 		size of the square.
	 */
	private Square(int size) {
		super(size, size);
	}
	
	/**
	 * The private constructor that call {@link Shape#Shape()} constructor. <br>
	 * this will create will create with {@link #DEFAULT_ROW} and {@link #DEFAULT_COLUMN}.
	 */
	private Square() {
		super();
	}
	
	/**
	 * get Square with any size by parameter.
	 *
	 * @param size
	 * 		the size of square
	 * @return square
	 */
	public static Square getSize(int size) {
		return new Square(size);
	}
	
	/**
	 * use:
	 * <ul>
	 * <li>{@link #DEFAULT_ROW default_row}={@value DEFAULT_ROW}</li>
	 * <li>{@link #DEFAULT_COLUMN default_column}={@value DEFAULT_COLUMN}</li>
	 * </ul>
	 * To create the square.
	 *
	 * @return Square with default size.
	 */
	public static Square getDefaultSize() {
		return new Square();
	}
}

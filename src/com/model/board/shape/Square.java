package com.model.board.shape;

/**
 * The Board Size with square shape (Both row and column are equal). <br>
 * To use this class you must use the method call {@link #getSize(int)} or {@link #getDefaultSize()}.
 *
 * @author bubblebitoey
 * @version 1.1
 * @since 2/23/2017 AD.
 */
public class Square extends Shape {
	/**
	 * The private constructor that call {@link Shape#Shape(int, int) Shape(size, size)} constructor.
	 *
	 * @param size
	 * 		size of the square.
	 * @throws com.model.board.shape.Shape.NegativeShapeException
	 * 		if input negative number.
	 */
	private Square(int size) throws NegativeShapeException {
		super(size, size);
	}
	
	/**
	 * The private constructor that call {@link Shape#Shape()} constructor. <br>
	 * this will create with {@link Shape#Shape()}
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
	 * @throws com.model.board.shape.Shape.NegativeShapeException
	 * 		if input negative number.
	 */
	public static Square getSize(int size) throws NegativeShapeException {
		return new Square(size);
	}
	
	/**
	 * this will create with {@link Shape#Shape()}
	 *
	 * @return Square with default size.
	 */
	public static Square getDefaultSize() {
		return new Square();
	}
}

package com.player;

/**
 * The position that represent the location of something in the board, or field.
 *
 * @author bubblebitoey
 * @since 2/23/2017 AD.
 */
public class Position {
	/**
	 * Warning: This variable start from 0. <br>
	 * this is variable that stand for <b>x-axis</b> in the board.
	 */
	public int x;
	
	/**
	 * Warning: This variable start from 0. <br>
	 * this is variable that stand for <b>y-axis</b> in the board.
	 */
	public int y;
	
	/**
	 * insert coordinate (x, y) which start from (1, 1).
	 *
	 * @param x
	 * 		coordinate x start from 1.
	 * @param y
	 * 		coordinate y start from 1.
	 */
	public Position(int x, int y) {
		this.x = x - 1;
		this.y = y - 1;
		nonNegative();
	}
	
	/**
	 * Set the new x position. <br>
	 * If set negative or invalid number this will change to be <b>zero</b>.
	 *
	 * @param x
	 * 		coordinate x that start from 0.
	 */
	public void setX(int x) {
		this.x = x;
		nonNegative();
	}
	
	/**
	 * Set the new y position. <br>
	 * If set negative or invalid number this will change to be <b>zero</b>.
	 *
	 * @param y
	 * 		coordinate y that start from 0.
	 */
	public void setY(int y) {
		this.y = y;
		nonNegative();
	}
	
	/**
	 * x and y variable must be positive number ( > 0).
	 */
	private void nonNegative() {
		if (this.x < 0) this.x = 0;
		if (this.y < 0) this.y = 0;
	}
	
	/**
	 * get string of the position that start from (1, 1)
	 *
	 * @return position format string
	 */
	@Override
	public String toString() {
		return "Position(" + (x + 1) + "," + (y + 1) + ")";
	}
	
	/**
	 * clone new position, use in case of if you want to modify the position without effect current position
	 *
	 * @return new position that same value but different variable
	 */
	@Override
	public Position clone() {
		return new Position(x + 1, y + 1);
	}
}

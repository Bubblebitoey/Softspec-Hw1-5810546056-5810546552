package com.player;

/**
 * The location of something in the board, or field.
 *
 * @author bubblebitoey
 * @since 2/23/2017 AD.
 */
public class Location {
	/**
	 * Warning: This variable start from 0. <br>
	 * this is variable that stand for <b>y-axis</b> in the board.
	 */
	public int row;
	
	/**
	 * Warning: This variable start from 0. <br>
	 * this is variable that stand for <b>x-axis</b> in the board.
	 */
	public int col;
	
	/**
	 * insert coordinate (row, column) which start from (1, 1).
	 *
	 * @param row
	 * 		The row (y-axis) start from 1.
	 * @param column
	 * 		The column (x-axis) start from 1.
	 */
	public Location(int row, int column) {
		this.row = row - 1;
		this.col = column - 1;
		// nonNegative();
	}
	
	/**
	 * Set the new row (y-axis). <br>
	 * If set negative or invalid number this will change to be <b>zero</b>.
	 *
	 * @param row
	 * 		The row (y-axis) that start from 0.
	 */
	public void setRow(int row) {
		this.row = row;
		// nonNegative();
	}
	
	/**
	 * Set the new column (x-axis). <br>
	 * If set negative or invalid number this will change to be <b>zero</b>.
	 *
	 * @param col
	 * 		The column (x-axis) that start from 0.
	 */
	public void setCol(int col) {
		this.col = col;
		// nonNegative();
	}
	
	/**
	 * row and col variable must be positive number ( > 0).
	 */
	@Deprecated
	private void nonNegative() {
		if (this.row < 0) this.row = 0;
		if (this.col < 0) this.col = 0;
	}
	
	/**
	 * get string of the position that start from (1, 1)
	 *
	 * @return position format string
	 */
	@Override
	public String toString() {
		return "Location(" + (row + 1) + "," + (col + 1) + ")";
	}
	
	/**
	 * clone new position, use in case of if you want to modify the position without effect current position
	 *
	 * @return new position that same value but different variable
	 */
	@Override
	public Location clone() {
		return new Location(row + 1, col + 1);
	}
}

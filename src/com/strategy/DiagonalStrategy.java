package com.strategy;

import com.board.Board;
import com.player.Location;

/**
 * Check winner by <b>diagonal</b>, <br>
 * This class contain 2 sub checker which is <b>left diagonal</b> and <b>right diagonal</b>.
 *
 * @author kamontat
 * @version 1.0
 * @since 2/24/2017 AD - 9:59 PM
 */
public class DiagonalStrategy extends WinStrategy {
	/**
	 * The checking direction (Must using in side this(DiagonalStrategy) class <b>ONLY</b>)
	 */
	private enum Direction {
		LEFT, RIGHT;
	}
	
	/**
	 * constructor that initial with board game.
	 *
	 * @param board
	 * 		The game board.
	 */
	public DiagonalStrategy(Board board) {
		super(board);
	}
	
	/**
	 * getting smallest position in diag left (position that most top left).
	 *
	 * @param p
	 * 		current position that want to minimise it.
	 * @return the minimise position to left.
	 */
	private Location toLeftMinimum(Location p) {
		Location newPos = new Location(1, 1);
		int max = Math.max(p.row, p.col);
		if (max == p.row && max != p.col) {
			newPos.setRow(max - p.col);
		} else if (max == p.col && max != p.row) {
			newPos.setCol(max - p.row);
		}
		return newPos;
	}
	
	/**
	 * getting smallest position in diag right (position that most top right).
	 *
	 * @param p
	 * 		current position that want to maximize it.
	 * @return the maximize position to right.
	 */
	private Location toRightMinimum(Location p) {
		Location newPos = p.clone();
		
		while (newPos.row > 0 && newPos.col < board.getColumn() - 1) {
			newPos.setRow(newPos.row - 1);
			newPos.setCol(newPos.col + 1);
		}
		return newPos;
	}
	
	/**
	 * check is win with direction (left or right).
	 *
	 * @param p
	 * 		the position that user insert their symbol.
	 * @param winCond
	 * 		the win condition.
	 * @param d
	 * 		the direction that want to check condition (\ or /).
	 * @return true player insert finish and he/she will win; otherwise, false.
	 */
	private boolean isWin(Location p, String winCond, Direction d) {
		String compareCond = "";
		Location min;
		
		if (d == Direction.LEFT) {
			min = toLeftMinimum(p);
		} else {
			min = toRightMinimum(p);
		}
		
		while (board.isValid(min)) {
			compareCond += board.getSymbol(min);
			
			if (d == Direction.LEFT) {
				// next (down right)
				min.setRow(min.row + 1);
				min.setCol(min.col + 1);
			} else {
				// next (down left)
				min.setRow(min.row + 1);
				min.setCol(min.col - 1);
			}
		}
		return compareCond.contains(winCond);
	}
	
	/**
	 * Check the diagonal both left and right.
	 *
	 * @param p
	 * 		the position that user insert their symbol.
	 * @param winCondition
	 * 		the win condition.
	 * @return true if win with diagonal win condition; otherwise, false.
	 */
	@Override
	public boolean execute(Location p, String winCondition) {
		return isWin(p, winCondition, Direction.LEFT) || isWin(p, winCondition, Direction.RIGHT);
	}
}

package com.strategy;

import com.board.Board;
import com.player.Location;
import com.player.Symbol;

/**
 * Check winner by <b>diagonal</b>, <br>
 * This class contain 2 sub checker which is <b>left diagonal</b> and <b>right diagonal</b>.
 *
 * @author kamontat
 * @version 2.1.1
 * @since 2/24/2017 AD - 9:59 PM
 */
public class DiagonalStrategy extends WinStrategy {
	/**
	 * Checking direction (Must using in side this(DiagonalStrategy) class <b>ONLY</b>)
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
	 * @param l
	 * 		current position that want to minimise it.
	 * @return the minimise position to left.
	 */
	private Location toLeftMinimum(Location l) {
		Location newPos = new Location(1, 1);
		int max = Math.max(l.row, l.col);
		if (max == l.row && max != l.col) {
			newPos.setRow(max - l.col);
		} else if (max == l.col && max != l.row) {
			newPos.setCol(max - l.row);
		}
		return newPos;
	}
	
	/**
	 * getting smallest position in diag right (position that most top right).
	 *
	 * @param l
	 * 		current position that want to maximize it.
	 * @return the maximize position to right.
	 */
	private Location toRightMinimum(Location l) {
		Location newPos = l.clone();
		
		while (newPos.row > 0 && newPos.col < board.getColumn() - 1) {
			newPos.setRow(newPos.row - 1);
			newPos.setCol(newPos.col + 1);
		}
		return newPos;
	}
	
	/**
	 * check is win with direction (left or right).
	 *
	 * @param l
	 * 		the position that user insert their character.
	 * @param winCond
	 * 		the win condition.
	 * @param d
	 * 		the direction that want to check condition (\ or /).
	 * @return true player insert finish and he/she will win; otherwise, false.
	 */
	private boolean isWin(Location l, String winCond, Direction d) {
		String compareCond = "";
		Location min;
		
		if (d == Direction.LEFT) {
			min = toLeftMinimum(l);
		} else {
			min = toRightMinimum(l);
		}
		
		while (board.isValid(min)) {
			// to ignore win symbol
			compareCond += board.getSymbol(min) == Symbol.WIN ? winCond.charAt(0): board.getSymbol(min);
			
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
	 * @param location
	 * 		the position that user insert their character.
	 * @param winCondition
	 * 		the win condition.
	 * @return true if win with diagonal win condition; otherwise, false.
	 */
	@Override
	public boolean execute(Location location, String winCondition) {
		return isWin(location, winCondition, Direction.LEFT) || isWin(location, winCondition, Direction.RIGHT);
	}
}

package com.strategy;

import com.board.Board;
import com.player.Location;

/**
 * Check winner by <b>column</b>.
 *
 * @author kamontat
 * @version 1.0.1
 * @since 2/24/2017 AD - 9:59 PM
 */
public class ColumnStrategy extends WinStrategy {
	
	/**
	 * constructor that initial with board game.
	 *
	 * @param board
	 * 		the game board.
	 */
	public ColumnStrategy(Board board) {
		super(board);
	}
	
	/**
	 * Check the column.
	 *
	 * @param l
	 * 		the position that user insert their character.
	 * @param winCondition
	 * 		the win condition.
	 * @return true if win with column win condition; otherwise, false.
	 */
	@Override
	public boolean execute(Location l, String winCondition) {
		Location newPos = l.clone();
		String compareCond = "";
		for (int i = 0; i < board.getSize().getRow(); i++) {
			newPos.setRow(i);
			compareCond += board.getSymbol(newPos).toString();
		}
		return compareCond.contains(winCondition);
	}
}

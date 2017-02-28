package com.strategy;

import com.board.Board;
import com.player.Location;

/**
 * Check winner by <b>row</b>.
 *
 * @author kamontat
 * @version 1.0.1
 * @since 2/24/2017 AD - 9:59 PM
 */
public class RowStrategy extends WinStrategy {
	
	/**
	 * constructor that initial with board game.
	 *
	 * @param board
	 * 		The game's board.
	 */
	public RowStrategy(Board board) {
		super(board);
	}
	
	/**
	 * Check the row.
	 *
	 * @param l
	 * 		the position that user insert their symbol.
	 * @param winCondition
	 * 		the win condition.
	 * @return true if win with row win condition; otherwise, false.
	 */
	@Override
	public boolean execute(Location l, String winCondition) {
		Location newPos = l.clone();
		String compareCond = "";
		for (int i = 0; i < board.getColumn(); i++) {
			newPos.setCol(i);
			compareCond += board.getSymbol(newPos).toString();
		}
		return compareCond.contains(winCondition);
	}
}

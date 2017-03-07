package com.model.algorithm;

import com.model.board.Board;
import com.model.player.Location;

/**
 * Check winner by <b>row</b>.
 *
 * @author kamontat
 * @version 1.0.1
 * @since 2/24/2017 AD - 9:59 PM
 */
public class ConsecutiveStrategy extends WinStrategy {
	
	/**
	 * constructor that initial with board game.
	 *
	 * @param board
	 * 		The game's board.
	 */
	public ConsecutiveStrategy(Board board) {
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
		Location newPos = (Location) l.clone();
		String compareCond = "";
		for (int i = 0; i < board.getSize().width; i++) {
			newPos.setCol(i);
			compareCond += board.getSymbol(newPos).toString();
		}
		return compareCond.contains(winCondition);
	}
}

package com.strategy;

import com.board.Board;
import com.player.Location;

/**
 * The win condition to win the game board
 *
 * @author kamontat
 * @version 1.0.1
 * @since 2/24/2017 AD - 9:57 PM
 */
public abstract class WinStrategy {
	Board board;
	
	/**
	 * constructor that initial with board game
	 *
	 * @param board
	 * 		the game board
	 */
	WinStrategy(Board board) {
		this.board = board;
	}
	
	/**
	 * Check winner by parameters
	 *
	 * @param l
	 * 		position that player insert their symbol
	 * @param winCondition
	 * 		the win condition that make player became winner if match this parameter
	 * @return return true, if player who insert the symbol became winner; otherwise return false
	 */
	public abstract boolean execute(Location l, String winCondition);
}

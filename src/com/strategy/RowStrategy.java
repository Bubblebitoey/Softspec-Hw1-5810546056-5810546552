package TicTacToe.strategy;

import TicTacToe.board.Board;
import TicTacToe.player.Position;

/**
 * Check winner by <b>row</b>.
 *
 * @author kamontat
 * @version 1.0
 * @since 2/24/2017 AD - 9:59 PM
 */
public class RowStrategy extends WinStrategy {
	
	/**
	 * constructor that initial with board game.
	 *
	 * @param board
	 * 		The game board.
	 */
	public RowStrategy(Board board) {
		super(board);
	}
	
	/**
	 * Check the row.
	 *
	 * @param p
	 * 		the position that user insert their symbol.
	 * @param winCondition
	 * 		the win condition.
	 * @return true if win with row win condition; otherwise, false.
	 */
	@Override
	public boolean execute(Position p, String winCondition) {
		String compareCond = "";
		for (int i = 0; i < board.getRow(); i++) {
			Position newPos = new Position(i, p.y);
			compareCond += board.getSymbol(newPos).toString();
		}
		return compareCond.contains(winCondition);
	}
}

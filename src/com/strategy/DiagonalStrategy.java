package TicTacToe.strategy;

import TicTacToe.board.Board;
import TicTacToe.player.Position;

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
	 * getting smallest position in diag right (position that most top right).
	 *
	 * @param p
	 * 		current position that want to minimise it.
	 * @return the minimise position to left.
	 */
	private Position toRightMinimum(Position p) {
		Position newPos = new Position(1, 1);
		int max = Math.max(p.x, p.y);
		if (max == p.x && max != p.y) {
			newPos.setX(max - p.y);
		} else if (max == p.y && max != p.x) {
			newPos.setY(max - p.x);
		}
		return newPos;
	}
	
	/**
	 * getting smallest position in diag left (position that most top left).
	 *
	 * @param p
	 * 		current position that want to maximize it.
	 * @return the maximize position to left.
	 */
	private Position toLeftMinimum(Position p) {
		Position newPos = p.clone();
		while (newPos.x > 1 && newPos.y < board.getColumn() - 1) {
			newPos.setX(newPos.x - 1);
			newPos.setY(newPos.y + 1);
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
	private boolean isWin(Position p, String winCond, Direction d) {
		String compareCond = "";
		Position min;
		
		if (d == Direction.LEFT) {
			min = toLeftMinimum(p);
		} else {
			min = toRightMinimum(p);
		}
		
		while (board.isValid(min)) {
			compareCond += board.getSymbol(min);
			
			if (d == Direction.LEFT) {
				min.setX(min.x + 1);
				min.setY(min.y - 1);
			} else {
				min.setX(min.x + 1);
				min.setY(min.y + 1);
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
	public boolean execute(Position p, String winCondition) {
		return isWin(p, winCondition, Direction.LEFT) || isWin(p, winCondition, Direction.RIGHT);
	}
}

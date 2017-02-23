package TicTacToe;

import static TicTacToe.Board.Condition.PLAYING;

/**
 * Created by bubblebitoey on 2/23/2017 AD.
 */
public class Board {
	public enum Condition {
		PLAYING, WIN, DRAW;
	}
	
	private int inRow = 5;
	private int row;
	private int col;
	private Symbol[][] board;
	public Condition condition = PLAYING;
	public Player winner;
	
	public Board(Square s) {
		this.row = s.row;
		this.col = s.column;
		board = new Symbol[row][col];
		
		// init board
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				board[i][j] = Symbol.EMPTY;
			}
		}
	}
	
	public void setWinCondition(int number) {
		inRow = number;
	}
	
	public boolean insert(Player player, Position p) {
		if (isValid(p) && isEmpty(p)) {
			board[p.x][p.y] = player.getSymbol();
			
			if (!isBoardEmpty()) condition = Condition.DRAW;
			else if (isWin(player, p)) {
				condition = Condition.WIN;
				winner = player;
			}
			
			return true;
		}
		return false;
	}
	
	private boolean isBoardEmpty() {
		for (Symbol[] symbols : board) {
			for (Symbol symbol : symbols) {
				if (symbol == Symbol.EMPTY) return true;
			}
		}
		return false;
	}
	
	private boolean isWin(Player player, Position p) {
		String compare = "";
		String winCondition = "";
		for (int i = 0; i < inRow; i++) {
			winCondition += player.getSymbol();
		}
		
		// row
		if (isRowWin(winCondition, p)) return true;
		
		// column
		if (isColWin(winCondition, p)) return true;
		
		// diag-left \
		Position minLeftPos = toLeftMinimum(p);
		if (isDiagLWin(winCondition, minLeftPos)) return true;
		
		// diag-right /
		Position minRightPos = toRightMinimum(p);
		if (isDiagRWin(winCondition, minRightPos)) return true;
		
		return false;
	}
	
	/**
	 * check row win condition
	 *
	 * @param winCond
	 * 		the win condition
	 * @param curr_pos
	 * 		the position that user insert their symbol
	 * @return true if win by row condition; otherwise, false
	 */
	private boolean isRowWin(String winCond, Position curr_pos) {
		String compareCond = "";
		for (int i = 0; i < row; i++) {
			compareCond += board[i][curr_pos.y];
		}
		return compareCond.contains(winCond);
	}
	
	/**
	 * check column win condition
	 *
	 * @param winCond
	 * 		the win condition
	 * @param curr_pos
	 * 		the position that user insert their symbol
	 * @return true if win by column condition; otherwise, false
	 */
	private boolean isColWin(String winCond, Position curr_pos) {
		String compareCond = "";
		for (int i = 0; i < this.col; i++) {
			compareCond += board[curr_pos.x][i];
		}
		return compareCond.contains(winCond);
	}
	
	/**
	 * check diag-left (\) win condition
	 *
	 * @param winCond
	 * 		the win condition
	 * @param min_curr_pos
	 * 		the minimum location in board (x=0, y=0)
	 * @return true if win by diag left (\) condition; otherwise, false
	 */
	private boolean isDiagLWin(String winCond, Position min_curr_pos) {
		String compareCond = "";
		while (isValid(min_curr_pos)) {
			compareCond += board[min_curr_pos.x][min_curr_pos.y];
			// next
			min_curr_pos.setX(min_curr_pos.x + 1);
			min_curr_pos.setY(min_curr_pos.y + 1);
		}
		return compareCond.contains(winCond);
	}
	
	/**
	 * check diag-right (/) win condition
	 *
	 * @param winCond
	 * 		the win condition
	 * @param min_curr_pos
	 * 		the minimum location in board (x=0, y=board.y.size-1)
	 * @return true if win by diag right (/) condition; otherwise, false
	 */
	private boolean isDiagRWin(String winCond, Position min_curr_pos) {
		String compareCond = "";
		while (isValid(min_curr_pos)) {
			compareCond += board[min_curr_pos.x][min_curr_pos.y];
			// next
			min_curr_pos.setX(min_curr_pos.x + 1);
			min_curr_pos.setY(min_curr_pos.y - 1);
		}
		return compareCond.contains(winCond);
	}
	
	/**
	 * must in board size
	 *
	 * @param p
	 * 		position
	 * @return true if in board size; otherwise, return false
	 */
	private boolean isValid(Position p) {
		return p.x >= 0 && p.x < row && p.y >= 0 && p.y < col;
	}
	
	/**
	 * insert position must empty
	 *
	 * @param p
	 * 		insert position
	 * @return true, if empty; otherwise, return false
	 */
	private boolean isEmpty(Position p) {
		return board[p.x][p.y] == Symbol.EMPTY;
	}
	
	/**
	 * getting smallest position in diag left (\)
	 *
	 * @param p
	 * @return
	 */
	private Position toLeftMinimum(Position p) {
		Position newPos = new Position(1, 1);
		int max = Math.max(p.x, p.y);
		if (max == p.x && max != p.y) {
			newPos.setX(max - p.y);
		} else if (max == p.y && max != p.x) {
			newPos.setY(max - p.x);
		}
		return newPos;
	}
	
	private Position toRightMinimum(Position p) {
		Position newPos = p.clone();
		while (newPos.x > 1 && newPos.y < col - 1) {
			newPos.setX(newPos.x - 1);
			newPos.setY(newPos.y + 1);
		}
		return newPos;
	}
	
	@Override
	public String toString() {
		String output = "0";
		for (int i = 1; i <= col; i++) {
			output += " " + i;
		}
		output += "\n";
		int i = 0;
		for (Symbol[] symbols : board) {
			output += ((++i) + " ");
			for (Symbol symbol : symbols) {
				output += (symbol + " ");
			}
			output += "\n";
		}
		return output;
	}
}

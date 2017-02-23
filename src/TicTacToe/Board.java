package TicTacToe;

import static TicTacToe.Board.Condition.PLAYING;

/**
 * Created by bubblebitoey on 2/23/2017 AD.
 */
public class Board {
	public enum Condition {
		PLAYING, WIN, DRAW;
	}
	
	private static final int inRow = 5;
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
		String winCondition = "";
		for (int i = 0; i < inRow; i++) {
			winCondition += player.getSymbol();
		}
		
		String row = "";
		for (int i = 0; i < this.row; i++) {
			row += board[i][p.y];
		}
		
		String col = "";
		for (int i = 0; i < this.col; i++) {
			col += board[p.x][i];
		}
		
		String diag = "";
		for (int i = 0; i < this.col; i++) {
			for (int j = 0; j < this.row; j++) {
				// do something
			}
			col += board[p.x][i];
		}
		
		return row.contains(winCondition) || col.contains(winCondition) || diag.contains(winCondition);
	}
	
	/**
	 * must in board size
	 *
	 * @param p
	 * 		position
	 * @return true if in board size; otherwise, return false
	 */
	public boolean isValid(Position p) {
		return p.x >= 0 && p.x < row && p.y >= 0 && p.y < col;
	}
	
	public boolean isEmpty(Position p) {
		return board[p.x][p.y] == Symbol.EMPTY;
	}
	
	@Override
	public String toString() {
		String output = "0 1 2 3 4 5 6 7 8 9 \n";
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

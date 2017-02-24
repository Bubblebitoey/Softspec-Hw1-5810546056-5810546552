package TicTacToe.board;

import TicTacToe.player.Player;
import TicTacToe.player.Position;
import TicTacToe.player.Symbol;
import TicTacToe.strategy.ColumnStrategy;
import TicTacToe.strategy.DiagonalStrategy;
import TicTacToe.strategy.RowStrategy;

import static TicTacToe.board.Board.Condition.PLAYING;

/**
 * Created by bubblebitoey on 2/23/2017 AD.
 */
public class Board {
	public enum Condition {
		PLAYING, WIN, DRAW;
	}
	
	private int inRow = 5;
	
	private Symbol[][] board;
	private int row;
	private int column;
	
	public Condition state = PLAYING;
	public Player winner;
	
	public Board(Square s) {
		this.row = s.row;
		this.column = s.column;
		board = new Symbol[row][column];
		
		// init board
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				board[i][j] = Symbol.EMPTY;
			}
		}
	}
	
	public Board(Square s, int winCondition) {
		this.row = s.row;
		this.column = s.column;
		board = new Symbol[row][column];
		this.inRow = winCondition;
		
		// init board
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
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
			
			if (!isBoardEmpty()) state = Condition.DRAW;
			else if (isWin(player, p)) {
				state = Condition.WIN;
				winner = player;
			}
			
			return true;
		}
		return false;
	}
	
	public Symbol getSymbol(Position position) {
		return board[position.x][position.y];
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	private boolean isBoardEmpty() {
		for (Symbol[] symbols : board) {
			for (Symbol symbol : symbols) {
				if (symbol == Symbol.EMPTY) return true;
			}
		}
		return false;
	}
	
	/**
	 * to check that current playing player win or not
	 *
	 * @param player
	 * 		current playing game player
	 * @param p
	 * 		the position that player playing
	 * @return true if this player already win
	 */
	private boolean isWin(Player player, Position p) {
		String compare = "";
		String winCondition = "";
		for (int i = 0; i < inRow; i++) {
			winCondition += player.getSymbol();
		}
		
		return new RowStrategy(this).execute(p, winCondition) || new ColumnStrategy(this).execute(p, winCondition) || new DiagonalStrategy(this).execute(p, winCondition);
	}
	
	/**
	 * must in board size
	 *
	 * @param p
	 * 		position
	 * @return true if in board size; otherwise, return false
	 */
	public boolean isValid(Position p) {
		return p.x >= 0 && p.x < row && p.y >= 0 && p.y < column;
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
	
	
	@Override
	public String toString() {
		String output = "0";
		for (int i = 1; i <= column; i++) {
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

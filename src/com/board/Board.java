package com.board;

import com.board.shape.Shape;
import com.history.BoardHistory;
import com.player.Player;
import com.player.Location;
import com.player.Symbol;
import com.strategy.ColumnStrategy;
import com.strategy.DiagonalStrategy;
import com.strategy.RowStrategy;

import static com.board.Board.State.ERROR;
import static com.board.Board.State.PLAYING;

/**
 * The board of the game that created by {@link com.board.shape.Shape Shape} class. <br>
 * In the board you can do:
 * <ol>
 * <li>{@link #insert(Player, Location)} - insert some symbol to that position.</li>
 * <li>{@link #toString()} - to get board in beautiful way to print.</li>
 * </ol>
 *
 * @author bubblebitoey
 * @since 2/23/2017 AD.
 */
public class Board {
	/**
	 * default connected character that make player win.
	 */
	private static final int DEFAULT_WIN_CONDITION = 5;
	
	/**
	 * The board state.
	 * <ul>
	 * <li>{@link State#PLAYING} - this board is playing</li>
	 * <li>{@link State#WIN} - this board have the winner already</li>
	 * <li>{@link State#DRAW} - this board end with draw condition (meaning no winner)</li>
	 * <li>{@link State#ERROR} - this board end with error exception</li>
	 * </ul>
	 */
	public enum State {
		PLAYING, WIN, DRAW, ERROR;
	}
	
	/**
	 * the win condition that player must place Symbol connected.
	 */
	private int inRow;
	
	private BoardHistory history = BoardHistory.getInstance();
	private Symbol[][] board;
	private int row;
	private int column;
	
	/**
	 * current state.
	 */
	public State state = PLAYING;
	
	/**
	 * the winner, if no winner this variable will be <code>null</code>.
	 */
	public Player winner;
	
	/**
	 * Create the board with the {@link Shape}. <br>
	 * Important: This Constructor will create board with {@link #DEFAULT_WIN_CONDITION} as game win condition
	 *
	 * @param s
	 * 		The board shape or the board size.
	 */
	public Board(Shape s) {
		this(s, DEFAULT_WIN_CONDITION);
	}
	
	/**
	 * Create the board with {@link Shape} and the win condition.
	 *
	 * @param s
	 * 		The board shape or the board size.
	 * @param winCondition
	 * 		the win condition (how long that symbol must connected).
	 */
	public Board(Shape s, int winCondition) {
		this.row = s.getRow();
		this.column = s.getColumn();
		board = new Symbol[row][column];
		this.inRow = winCondition > row ? winCondition > column ? Math.max(row, column): winCondition: winCondition;
		
		// init board
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				board[i][j] = Symbol.EMPTY;
			}
		}
	}
	
	/**
	 * Insert the player symbol ({@link Player#getSymbol()}) at position {@link Location p} in the board.
	 *
	 * @param player
	 * 		the current playing player.
	 * @param p
	 * 		the position.
	 * @return true, if insert complete; otherwise, return false.
	 */
	public boolean insert(Player player, Location p) {
		if (state == ERROR) return false;
		if (p == null) {
			System.err.println("Invalid position.");
			return false;
		}
		if (!isValid(p)) {
			System.err.println(p + " is out of board size.");
			return false;
		}
		if (!isEmpty(p)) {
			System.err.println(p + " is not empty location.");
			return false;
		}
		
		board[p.row][p.col] = player.getSymbol();
		
		// change board state.
		if (checkWin(player, p)) {
			board[p.row][p.col] = Symbol.WIN;
			state = State.WIN;
			winner = player;
		} else if (isBoardFull()) {
			state = State.DRAW;
		}
		
		history.addNewHistory(this, player, p);
		return true;
	}
	
	/**
	 * get the symbol in the board by {@link Location position}.
	 *
	 * @param position
	 * 		the position that want to get symbol.
	 * @return Symbol at that position.
	 */
	public Symbol getSymbol(Location position) {
		return board[position.row][position.col];
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	/**
	 * get the board history.
	 *
	 * @return the history of current board.
	 * @see BoardHistory
	 */
	public BoardHistory getHistory() {
		return history;
	}
	
	/**
	 * check is the board full or not.
	 *
	 * @return true, if board already fulled; otherwise return false.
	 */
	public boolean isBoardFull() {
		for (Symbol[] symbols : board) {
			for (Symbol symbol : symbols) {
				if (symbol == Symbol.EMPTY) return false;
			}
		}
		return true;
	}
	
	/**
	 * To check that current playing player win or not. <br>
	 * This method using Strategy patterns which in the strategy package ({@link com.strategy.WinStrategy}, etc.).
	 *
	 * @param player
	 * 		current playing game player.
	 * @param p
	 * 		the position that player playing.
	 * @return true if this player already win.
	 */
	private boolean checkWin(Player player, Location p) {
		String compare = "";
		String winCondition = "";
		for (int i = 0; i < inRow; i++) {
			winCondition += player.getSymbol();
		}
		
		return new RowStrategy(this).execute(p, winCondition) || new ColumnStrategy(this).execute(p, winCondition) || new DiagonalStrategy(this).execute(p, winCondition);
	}
	
	/**
	 * must in board size.
	 *
	 * @param p
	 * 		position.
	 * @return true if in board size; otherwise, return false.
	 */
	public boolean isValid(Location p) {
		return p != null && p.row >= 0 && p.row < row && p.col >= 0 && p.col < column;
	}
	
	/**
	 * is the position empty.
	 *
	 * @param p
	 * 		insert position.
	 * @return true, if empty; otherwise, return false.
	 */
	public boolean isEmpty(Location p) {
		return isValid(p) && board[p.row][p.col] == Symbol.EMPTY;
	}
	
	/**
	 * print the history out.
	 */
	public void printHistory() {
		System.out.println(history.toString());
	}
	
	@Override
	public String toString() {
		String output = " 0 ";
		for (int i = 1; i <= column; i++) {
			output += String.format("%2d%1s", i, "");
		}
		output += "\n";
		int i = 0;
		for (Symbol[] symbols : board) {
			output += String.format("%2d%1s", (++i), "");
			for (Symbol symbol : symbols) {
				if (symbol != Symbol.WIN) output += String.format("%2s%1s", symbol, "");
				else output += String.format("%-3s", symbol);
			}
			output += "\n";
		}
		return output;
	}
	
	/**
	 * Warning: use this method only when have error occurred that game cannot continues play.
	 */
	public void fail() {
		state = ERROR;
		// System.exit(1);
	}
}

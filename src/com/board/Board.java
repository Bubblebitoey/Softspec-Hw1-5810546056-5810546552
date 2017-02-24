package com.board;

import com.board.shape.Shape;
import com.player.Player;
import com.player.Position;
import com.player.Symbol;
import com.strategy.ColumnStrategy;
import com.strategy.DiagonalStrategy;
import com.strategy.RowStrategy;

import static com.board.Board.State.PLAYING;

/**
 * The board of the game that created by {@link com.board.shape.Shape Shape} class. <br>
 * In the board you can do:
 * <ol>
 * <li>{@link #insert(Player, Position)} - insert some symbol to that position.</li>
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
	 */
	public enum State {
		PLAYING, WIN, DRAW;
	}
	
	/**
	 * the win condition that player must place Symbol connected.
	 */
	private int inRow;
	
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
		this.inRow = winCondition;
		
		// init board
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				board[i][j] = Symbol.EMPTY;
			}
		}
	}
	
	/**
	 * Insert the player symbol ({@link Player#getSymbol()}) at position {@link Position p} in the board.
	 *
	 * @param player
	 * 		the current playing player.
	 * @param p
	 * 		the position.
	 * @return true, if insert complete; otherwise, return false.
	 */
	public boolean insert(Player player, Position p) {
		if (isValid(p) && isEmpty(p)) {
			board[p.x][p.y] = player.getSymbol();
			
			if (isBoardFull()) state = State.DRAW;
			else if (isWin(player, p)) {
				state = State.WIN;
				winner = player;
			}
			
			return true;
		}
		return false;
	}
	
	/**
	 * get the symbol in the board by {@link Position position}.
	 *
	 * @param position
	 * 		the position that want to get symbol.
	 * @return Symbol at that position.
	 */
	public Symbol getSymbol(Position position) {
		return board[position.x][position.y];
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	/**
	 * check is the board full or not.
	 *
	 * @return true, if board already fulled; otherwise return false.
	 */
	private boolean isBoardFull() {
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
	private boolean isWin(Player player, Position p) {
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
	public boolean isValid(Position p) {
		return p.x >= 0 && p.x < row && p.y >= 0 && p.y < column;
	}
	
	/**
	 * is the position empty.
	 *
	 * @param p
	 * 		insert position.
	 * @return true, if empty; otherwise, return false.
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

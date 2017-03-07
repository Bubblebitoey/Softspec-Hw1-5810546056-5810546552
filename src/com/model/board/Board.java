package com.model.board;

import com.model.board.shape.Shape;
import com.model.history.BoardHistory;
import com.model.player.Location;
import com.model.player.Player;
import com.model.player.Symbol;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * The board of the game that created by {@link com.model.board.shape.Shape Shape} class. <br>
 * In the board you can do:
 * <ol>
 * <li>{@link #insert(Player, Location)} - insert some symbol to that position.</li>
 * <li>{@link #toString()} - to get board in beautiful way to print.</li>
 * </ol>
 *
 * @author bubblebitoey
 * @version 3.2.1
 * @since 2/23/2017 AD.
 */
public class Board {
	/**
	 * default the player who succeeds in placing consecutive of their character in a horizontal, vertical, or diagonal size.getH() wins the game.
	 */
	private static final int DEFAULT_WIN_CONDITION = 5;
	
	/**
	 * the win condition player who succeeds in placing consecutive of their character in a horizontal, vertical, or diagonal size.getH() wins the game.
	 */
	private int consecutive;
	
	private BoardHistory history;
	private Symbol[][] board;
	private Shape size;
	
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
	 * 		the win condition (how many consecutive characters in a horizontal, vertical, or diagonal size.getH()).
	 */
	public Board(Shape s, int winCondition) {
		size = s;
		board = new Symbol[size.height][size.width];
		this.consecutive = winCondition > size.height || winCondition < 1 ? winCondition > size.width || winCondition < 1 ? Math.max(size.height, size.width): winCondition: winCondition;
		
		resetBoard();
	}
	
	public void resetBoard() {
		history = BoardHistory.getInstance();
		history.clearAllHistory();
		
		// init board
		for (int i = 0; i < size.height; i++) {
			for (int j = 0; j < size.width; j++) {
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
		if (player == null || p == null) return false;
		
		if (!isValid(p)) {
			System.err.println(p + " is out of board size.");
			return false;
		}
		if (!isEmpty(p)) {
			System.err.println(p + " is not empty location.");
			return false;
		}
		
		board[p.row][p.col] = player.getSymbol();
		
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
	
	/**
	 * set new symbol in to the board at {@link Location location}.
	 *
	 * @param location
	 * 		the location
	 * @param symbol
	 * 		new symbol
	 */
	public void setSymbol(Location location, Symbol symbol) {
		board[location.row][location.col] = symbol;
	}
	
	public Shape getSize() {
		return size;
	}
	
	public int getConsecutive() {
		return consecutive;
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
	public boolean isFull() {
		for (Symbol[] symbols : board) {
			for (Symbol symbol : symbols) {
				if (symbol == Symbol.EMPTY) return false;
			}
		}
		return true;
	}
	
	/**
	 * must in board size.
	 *
	 * @param p
	 * 		position.
	 * @return true if in board size; otherwise, return false.
	 */
	public boolean isValid(Location p) {
		return p != null && p.row >= 0 && p.row < size.height && p.col >= 0 && p.col < size.width;
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
	
	/**
	 * save to history to the file
	 *
	 * @param file
	 * 		saving history file
	 * @return true, if save successfully; otherwise false
	 */
	public boolean saveHistory(File file) {
		try {
			PrintWriter printer = new PrintWriter(new FileOutputStream(file));
			printer.print(history.toString());
			printer.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public String toString() {
		String output = " 0 ";
		for (int i = 1; i <= size.width; i++) {
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
}

package com.controller;

import com.model.board.Board;
import com.model.board.shape.Shape;
import com.model.player.Location;
import com.model.player.Player;

import java.io.*;

/**
 * @author kamontat
 * @version 1.0
 * @since Thu 02/Mar/2017 - 12:32 PM
 */
public interface GameBoard {
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
		INIT, PLAYING, END, WIN, DRAW, ERROR;
	}
	
	/**
	 * get maximum player that game can add.
	 */
	public int getMaximumPlayers();
	
	/**
	 * Insert location of current player
	 * To check game's state
	 *
	 * @param location
	 * 		This is location of current player
	 * @return true if insertion complete; otherwise, return false.
	 */
	public boolean insert(Location location);
	
	/**
	 * Get the current playing player
	 *
	 * @return the current player
	 */
	public Player currentPlayer();
	
	/**
	 * Switch player to next player
	 */
	public void nextPlayer();
	
	/**
	 * The state of game is playing
	 */
	public void start();
	
	/**
	 * The state of game is end
	 */
	public void end();
	
	/**
	 * When have error occured. This method will call
	 */
	public void fail();
	
	/**
	 * Restart the game by reset game's board and reset player
	 */
	public void restart();
	
	/**
	 * Get the winner of current game
	 *
	 * @return The winner of current game
	 */
	public Player getWinner();
	
	/**
	 * Get state of current game
	 *
	 * @return State of current game
	 */
	public State getGameState();
	
	/**
	 * Get size of game's board
	 *
	 * @return Size of game's board
	 */
	public Shape getSize();
	
	/**
	 * To print game's board in console
	 */
	public void printBoard();
	
	/**
	 * save board's information into file
	 *
	 * @param board
	 * 		is Game's board
	 * @param f
	 * 		is file
	 */
	default public void save(Board board, File f) {
		try {
			// try to create file
			f.getParentFile().mkdir();
			f.createNewFile();
			
			if (f.exists()) {
				PrintWriter printer = new PrintWriter(new FileOutputStream(f));
				printer.print(board.getHistory().toString());
				printer.close();
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}

package com.controller;

import com.model.board.Board;
import com.model.board.shape.Shape;
import com.model.player.Location;
import com.model.player.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

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
	
	public boolean insert(Location location);
	
	public Player currentPlayer();
	
	public void nextPlayer();
	
	public void start();
	
	public void end();
	
	public void fail();
	
	public void restart();
	
	public Player getWinner();
	
	public State getGameState();
	
	public Shape getSize();
	
	public void printBoard();
	
	default public void save(Board board, File f) {
		try {
			PrintWriter printer = new PrintWriter(new FileOutputStream(f));
			printer.print(board.getHistory().toString());
			printer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

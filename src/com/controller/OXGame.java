package com.controller;

import com.board.Board;
import com.player.Location;
import com.player.Player;

/**
 * This class will play the Tic-Tac-Toe game, <br>
 * You can play by using {@link OXGame#OXGame(Board, Player...)} - this will allow you to play the game in console.
 *
 * @author bubblebitoey
 * @version 3.2
 * @since 2/23/2017 AD.
 */
public class OXGame {
	/**
	 * The game board.
	 */
	private Board board;
	
	/**
	 * Current playing player.
	 */
	private int currentPlayer;
	
	/**
	 * All players.
	 */
	private Player[] players;
	
	/**
	 * use default input stream to play the game that allow you to play the game in console such as <code>terminal</code> or <code>compiler</code> <br>
	 * To setting the game you must have <b>The board</b> and <b>The player</b>.
	 *
	 * @param board
	 * 		This is the game's board.
	 * @param players
	 * 		This is the players.
	 */
	public OXGame(Board board, Player... players) {
		this.board = board;
		this.players = players;
	}
	
	public boolean insert(Location l) {
		return board.insert(getCurrentPlayer(), l);
	}
	
	public void fail() {
		board.fail();
	}
	
	public void printBoard() {
		System.out.println(board.toString());
	}
	
	public Board.State getBoardState() {
		return board.state;
	}
	
	public Player getWinner() {
		return board.winner;
	}
	
	public int row() {
		return board.getRow();
	}
	
	public int column() {
		return board.getColumn();
	}
	
	/**
	 * swap the player to the next player.
	 */
	public void swapPlayer() {
		// next
		currentPlayer++;
		// if out of player length
		if (currentPlayer == players.length) currentPlayer = 0;
	}
	
	/**
	 * get the current playing player
	 *
	 * @return the current player
	 */
	public Player getCurrentPlayer() {
		return players[currentPlayer];
	}
}

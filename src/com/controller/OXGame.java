package com.controller;

import com.board.Board;
import com.board.shape.Shape;
import com.player.Location;
import com.player.Player;
import com.player.Symbol;
import com.strategy.ColumnStrategy;
import com.strategy.DiagonalStrategy;
import com.strategy.RowStrategy;

/**
 * This class will play the Tic-Tac-Toe game, <br>
 * You can play by using {@link OXGame#OXGame(Board, Player...)} - this will allow you to play the game in console.
 *
 * @author bubblebitoey
 * @version 4.0
 * @since 2/23/2017 AD.
 */
public class OXGame implements GameBoard {
	/**
	 * The game board.
	 */
	private Board board;
	private State state;
	
	/**
	 * Index of current playing player.
	 */
	private int currentPlayer;
	
	/**
	 * Index of the winner
	 */
	private int winner;
	
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
		
		state = State.PLAYING;
	}
	
	@Override
	public boolean insert(Location location) {
		if (board.insert(currentPlayer(), location)) {
			// change game state.
			if (checkWin(currentPlayer(), location)) {
				board.setSymbol(location, Symbol.WIN);
				state = State.WIN;
				winner = currentPlayer;
			} else if (board.isFull()) {
				state = State.DRAW;
			}
			return true;
		}
		return false;
	}
	
	/**
	 * To check that current player win or not. <br>
	 * This method using Strategy patterns which in the strategy package ({@link com.strategy.WinStrategy}, etc.).
	 *
	 * @param player
	 * 		current game player.
	 * @param p
	 * 		the position that player playing.
	 * @return true if this player already win.
	 */
	private boolean checkWin(Player player, Location p) {
		String compare = "";
		String winCondition = "";
		for (int i = 0; i < board.getConsecutive(); i++) {
			winCondition += player.getSymbol();
		}
		
		return new RowStrategy(board).execute(p, winCondition) || new ColumnStrategy(board).execute(p, winCondition) || new DiagonalStrategy(board).execute(p, winCondition);
	}
	
	/**
	 * Warning: use this method only when have error occurred that game cannot continues play.
	 */
	@Override
	public void fail() {
		state = State.ERROR;
		// System.exit(1);
	}
	
	@Override
	public Player getWinner() {
		return players[winner];
	}
	
	@Override
	public State getGameState() {
		return state;
	}
	
	@Override
	public Shape getSize() {
		return board.getSize();
	}
	
	/**
	 * get the current playing player
	 *
	 * @return the current player
	 */
	@Override
	public Player currentPlayer() {
		return players[currentPlayer];
	}
	
	/**
	 * swap the player to the next player.
	 */
	@Override
	public void nextPlayer() {
		// next
		currentPlayer++;
		// if out of player length
		if (currentPlayer == players.length) currentPlayer = 0;
	}
	
	@Override
	public void printBoard() {
		System.out.println(board.toString());
	}
	
	public int row() {
		return getSize().getRow();
	}
	
	public int column() {
		return getSize().getColumn();
	}
}

package com.console;

import com.board.Board;
import com.player.Player;
import com.player.Position;

import java.io.InputStream;
import java.util.*;

/**
 * This class will play the Tic-Tac-Toe game, <br>
 * There are 2 way to play the game:
 * <ol>
 * <li>You can player by using {@link OXGame#OXGame(Board, Player...)} - this will allow you to play the game in console.</li>
 * <li>Another way is {@link OXGame#OXGame(InputStream, Board, Player...)} - this will allow you to play the game with any input stream that you wanted.</li>
 * </ol>
 * <p>
 * To run the game just call method {@link #run()}
 *
 * @author bubblebitoey
 * @since 2/23/2017 AD.
 */
public class OXGame implements Runnable {
	private static final InputStream DEFAULT_INPUT_STREAM = System.in;
	
	private Scanner input;
	
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
	 * use default input stream to player the game that allow you to playing the game in console like <code>terminal</code> or <code>compiler</code> <br>
	 * To setting the game you must have <b>The board</b> and <b>The player</b>.
	 *
	 * @param board
	 * 		This is the game board.
	 * @param players
	 * 		This is the players.
	 */
	public OXGame(Board board, Player... players) {
		this(DEFAULT_INPUT_STREAM, board, players);
	}
	
	/**
	 * this constructor will use optional InputStream as parameter that allow you to pass any input stream that you want inside the game.
	 *
	 * @param s
	 * 		any input stream type (like file, web, etc.).
	 * @param board
	 * 		the game board.
	 * @param players
	 * 		the game players.
	 */
	public OXGame(InputStream s, Board board, Player... players) {
		input = new Scanner(s);
		this.board = board;
		this.players = players;
	}
	
	/**
	 * ask of input position.
	 *
	 * @return the position that parse from the input.
	 */
	private Position input() {
		System.out.println(String.format("Player %s's turn.", getCurrentPlayer()));
		try {
			System.out.print("Please select row: ");
			int row = input.nextInt();
			
			System.out.print("Please select column: ");
			int col = input.nextInt();
			
			return new Position(row, col);
		} catch (InputMismatchException e) {
			return null;
		}
	}
	
	/**
	 * swap the player to the next player.
	 */
	private void swapPlayer() {
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
	private Player getCurrentPlayer() {
		return players[currentPlayer];
	}
	
	/**
	 * Run the Tic-Tac-Toe game, until game over.
	 */
	@Override
	public void run() {
		System.out.println(board);
		while (board.state == Board.State.PLAYING) {
			Position p = input();
			if (p == null) System.out.println("Accepted only number");
			boolean isSuccess = board.insert(getCurrentPlayer(), p);
			if (isSuccess) {
				System.out.println(board);
				if (board.state == Board.State.PLAYING) swapPlayer();
			} else {
				System.out.println("Invalid row or column");
			}
		}
		
		if (board.state == Board.State.DRAW) {
			System.out.println("Draw.");
		} else if (board.state == Board.State.WIN) {
			System.out.println(board.winner + " winner.");
		}
	}
}

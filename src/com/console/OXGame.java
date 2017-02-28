package com.console;

import com.board.Board;
import com.player.Location;
import com.player.Player;

import java.io.InputStream;
import java.util.*;

/**
 * This class will play the Tic-Tac-Toe game, <br>
 * There are 2 way to play the game:
 * <ol>
 * <li>You can play by using {@link OXGame#OXGame(Board, Player...)} - this will allow you to play the game in console.</li>
 * <li>Another way is {@link OXGame#OXGame(InputStream, Board, Player...)} - this will allow you to play the game with any input stream that you want.</li>
 * </ol>
 * <p>
 * To run the game just call method {@link #run()}
 *
 * @author bubblebitoey
 * @version 3.0
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
	 * use default input stream to play the game that allow you to play the game in console such as <code>terminal</code> or <code>compiler</code> <br>
	 * To setting the game you must have <b>The board</b> and <b>The player</b>.
	 *
	 * @param board
	 * 		This is the game's board.
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
	private Location input() {
		System.out.println(String.format("Player %s's turn.", getCurrentPlayer()));
		try {
			System.out.print("Please select row: ");
			int row = input.nextInt();
			
			System.out.print("Please select column: ");
			int col = input.nextInt();
			
			return new Location(row, col);
		} catch (InputMismatchException e) {
			input.nextLine();
			return null;
		} catch (NoSuchElementException e) {
			board.fail();
			throw e;
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
			Location p = input();
			
			if (p == null) {
				System.err.println("Accepted only number");
			} else {
				boolean success = board.insert(getCurrentPlayer(), p);
				if (success) {
					System.out.println(board);
					if (board.state == Board.State.PLAYING) swapPlayer();
				}
			}
		}
		
		if (board.state == Board.State.DRAW) {
			System.out.println("Draw.");
		} else if (board.state == Board.State.WIN) {
			System.out.println(board.winner + " winner.");
		}
	}
}

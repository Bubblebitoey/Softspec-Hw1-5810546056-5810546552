package com.console;

import com.board.Board;
import com.controller.OXGame;
import com.player.Location;
import com.player.Player;

import java.io.InputStream;
import java.util.*;

/**
 * @author bubblebitoey
 * @version 1.0
 * @since 3/2/2017 AD.
 */
public class Console {
	public static final InputStream DEFAULT_INPUT_STREAM = System.in;
	
	private Scanner input;
	private OXGame game;
	
	public Console(InputStream inputStream, OXGame game) {
		input = new Scanner(inputStream);
		this.game = game;
	}
	
	/**
	 * ask of input position.
	 *
	 * @return the position that parse from the input.
	 */
	private Location input() {
		System.out.println(String.format("Player %s's turn.", game.getCurrentPlayer()));
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
			game.fail();
			throw e;
		}
	}
	
	/**
	 * play the game
	 */
	public void play() {
		game.printBoard();
		while (game.getBoardState() == Board.State.PLAYING) {
			Location p = input();
			
			if (p == null) {
				System.err.println("Accepted only number");
			} else {
				boolean success = game.insert(p);
				if (success) {
					game.printBoard();
					if (game.getBoardState() == Board.State.PLAYING) game.swapPlayer();
				}
			}
		}
		
		if (game.getBoardState() == Board.State.DRAW) {
			System.out.println("Draw.");
		} else if (game.getBoardState() == Board.State.WIN) {
			System.out.println(game.getWinner() + " winner.");
		}
	}
}

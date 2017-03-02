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
			game.getBoard().fail();
			throw e;
		}
	}
	
	/**
	 * play the game
	 */
	public void play() {
		System.out.println(game.getBoard());
		while (game.getBoard().state == Board.State.PLAYING) {
			Location p = input();
			
			if (p == null) {
				System.err.println("Accepted only number");
			} else {
				boolean success = game.getBoard().insert(game.getCurrentPlayer(), p);
				if (success) {
					System.out.println(game.getBoard());
					if (game.getBoard().state == Board.State.PLAYING) game.swapPlayer();
				}
			}
		}
		
		if (game.getBoard().state == Board.State.DRAW) {
			System.out.println("Draw.");
		} else if (game.getBoard().state == Board.State.WIN) {
			System.out.println(game.getBoard().winner + " winner.");
		}
	}
}

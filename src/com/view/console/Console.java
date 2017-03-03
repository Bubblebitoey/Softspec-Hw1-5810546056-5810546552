package com.view.console;

import com.controller.GameBoard;
import com.model.player.Location;

import java.io.InputStream;
import java.util.*;

/**
 * @author bubblebitoey
 * @version 1.1
 * @since 3/2/2017 AD.
 */
public class Console implements Runnable {
	public static final InputStream DEFAULT_INPUT_STREAM = System.in;
	
	private Scanner input;
	private GameBoard game;
	
	public Console(InputStream inputStream, GameBoard game) {
		input = new Scanner(inputStream);
		this.game = game;
	}
	
	/**
	 * ask of input position.
	 *
	 * @return the position that parse from the input.
	 */
	private Location input() {
		System.out.println(String.format("Player %s's turn.", game.currentPlayer()));
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
	
	@Override
	public void run() {
		game.start();
		game.printBoard();
		
		while (game.getGameState() == GameBoard.State.PLAYING) {
			Location p = input();
			
			if (p == null) {
				System.err.println("Accepted only number");
			} else {
				boolean success = game.insert(p);
				if (success) {
					game.printBoard();
					if (game.getGameState() == GameBoard.State.PLAYING) game.nextPlayer();
				}
			}
		}
		
		if (game.getGameState() == GameBoard.State.DRAW) {
			System.out.println("Draw.");
		} else if (game.getGameState() == GameBoard.State.WIN) {
			System.out.println(game.getWinner() + " winner.");
		}
	}
}

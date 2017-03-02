package com.main;

import com.board.Board;
import com.board.shape.Square;
import com.console.GUI;
import com.controller.OXGame;
import com.player.Player;
import com.player.Symbol;

/**
 * Main class
 *
 * @author bubblebitoey
 * @version 3.0
 * @since 2/23/2017 AD.
 */
public class Main {
	
	/**
	 * main method to run the game
	 *
	 * @param args
	 * 		no used
	 */
	public static void main(String[] args) {
		Board b = new Board(Square.getDefaultSize());
		
		Player p1 = new Player("p1", Symbol.O);
		Player p2 = new Player("p2", Symbol.X);
		
		OXGame game = new OXGame(b, p1, p2);
		
		// console version
		// Runnable runnable = new Console(Console.DEFAULT_INPUT_STREAM, game);
		// gui version
		Runnable runnable = new GUI(game);
		
		runnable.run();
		
		// for save to result to file
		// game.save(b, new File("src/test/testfile/temp/finish_game_random_number.txt"));
	}
}

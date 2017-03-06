package com.main;

import com.view.gui.SettingPage;

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
		// game creator for console version only
		// Board b = new Board(Square.getDefaultSize());
		// Player p1 = new Player("p1", Symbol.O);
		// Player p2 = new Player("p2", Symbol.X);
		// GameBoard game = new OXGame(b, p1, p2);
		
		// console version
		// Runnable runnable = new Console(Console.DEFAULT_INPUT_STREAM, game);
		
		// gui version
		Runnable runnable = new SettingPage();
		
		// run code
		runnable.run();
		
		// for save to result to file
		// game.save(b, new File("src/test/testfile/temp/finish_game_random_number.txt"));
	}
}

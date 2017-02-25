package com.main;

import com.board.Board;
import com.board.shape.Shape;
import com.board.shape.Square;
import com.console.OXGame;
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
		Shape s = Square.getSize(30);
		Board b = new Board(s, 10);
		
		Player p1 = new Player("p1", Symbol.O);
		Player p2 = new Player("p2", Symbol.X);
		
		// For testing only
		// InputStream stream = Randoms.getRandomInputStream(1750, s.getRow());
		// OXGame game = new OXGame(stream, b, p1, p2);
		
		OXGame game = new OXGame(b, p1, p2);
		game.run();
		
		// for save to result to file
		// File file = new File("src/test/testfile/temp/finish_game_random_number.txt");
		// PrintWriter printer = new PrintWriter(new FileOutputStream(file));
		// printer.print(b.getHistory().toString());
		// printer.close();
		
	}
}

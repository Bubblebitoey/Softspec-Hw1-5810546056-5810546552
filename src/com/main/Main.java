package com.main;

import com.board.Board;
import com.board.shape.Square;
import com.console.Game;
import com.player.Player;
import com.player.Symbol;

/**
 * Created by bubblebitoey on 2/23/2017 AD.
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
		
		Player p1 = new Player("net", Symbol.O);
		Player p2 = new Player("bitoey", Symbol.X);
		
		Game game = new Game(b, p1, p2);
		game.run();
	}
}

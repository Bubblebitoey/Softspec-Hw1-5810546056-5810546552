package com.main;

import com.board.Board;
import com.board.shape.Shape;
import com.board.shape.Square;
import com.console.OXGame;
import com.player.Player;
import com.player.Symbol;

/**
 * Created by bubblebitoey on 2/23/2017 AD.
 */
public class Main {
	public static Shape s = Square.getSize(5);
	
	/**
	 * main method to run the game
	 *
	 * @param args
	 * 		no used
	 */
	public static void main(String[] args) {
		Board b = new Board(s);
		
		Player p1 = new Player("p1", Symbol.O);
		Player p2 = new Player("p2", Symbol.X);
		
		OXGame game = new OXGame(b, p1, p2);
		game.run();
		
		b.printHistory();
	}
}

package TicTacToe.main;

import TicTacToe.console.Game;
import TicTacToe.player.Player;
import TicTacToe.player.Symbol;
import TicTacToe.board.Board;
import TicTacToe.board.Square;

/**
 * Created by bubblebitoey on 2/23/2017 AD.
 */
public class Main {
	public static void main(String[] args) {
		Board b = new Board(Square.getSize(3), 3);
		
		Player p1 = new Player("net", Symbol.O);
		Player p2 = new Player("bitoey", Symbol.X);
		
		Game game = new Game(b, p1, p2);
		game.run();
	}
}

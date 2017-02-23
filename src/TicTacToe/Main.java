package TicTacToe;

/**
 * Created by bubblebitoey on 2/23/2017 AD.
 */
public class Main {
	public static void main(String[] args) {
		Player p1 = new Player("p1", Symbol.O);
		Player p2 = new Player("p2", Symbol.X);
		
		Game game = new Game(new Board(new Square()), p1, p2);
		game.run();
	}
}

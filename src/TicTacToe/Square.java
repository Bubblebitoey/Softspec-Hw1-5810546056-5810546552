package TicTacToe;

/**
 * Created by bubblebitoey on 2/23/2017 AD.
 */
public class Square {
	protected final int row = 9;
	protected final int column = 9;
	
	public static Square getSize() {
		return new Square();
	}
}

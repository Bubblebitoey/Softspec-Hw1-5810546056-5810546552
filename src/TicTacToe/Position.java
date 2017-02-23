package TicTacToe;

/**
 * Created by bubblebitoey on 2/23/2017 AD.
 */
public class Position {
	public int x;
	public int y;
	
	/**
	 * insert coordinate (x, y) which start from (1, 1)
	 *
	 * @param x
	 * 		coordinate x start from 1
	 * @param y
	 * 		coordinate y start from 1
	 */
	public Position(int x, int y) {
		this.x = x - 1;
		this.y = y - 1;
	}
}

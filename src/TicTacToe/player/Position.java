package TicTacToe.player;

/**
 * Created by bubblebitoey on 2/23/2017 AD.
 */
public class Position {
	public int x; // start from 0
	public int y; // start from 0
	
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
		nonNegative();
	}
	
	public void setX(int x) {
		this.x = x;
		nonNegative();
	}
	
	public void setY(int y) {
		this.y = y;
		nonNegative();
	}
	
	private void nonNegative() {
		if (this.x < 0) this.x = 0;
		if (this.y < 0) this.y = 0;
	}
	
	@Override
	public String toString() {
		return "Position(" + x + "," + y + ")";
	}
	
	@Override
	public Position clone() {
		return new Position(x + 1, y + 1);
	}
}

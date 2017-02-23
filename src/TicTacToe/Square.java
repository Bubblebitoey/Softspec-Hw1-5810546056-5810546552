package TicTacToe;

/**
 * Created by bubblebitoey on 2/23/2017 AD.
 */
public class Square {
	protected final int DEFAULT_ROW = 9;
	protected final int DEFAULT_COLUMN = 9;
	
	public final int row;
	public final int column;
	
	private Square(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	private Square() {
		this.row = DEFAULT_ROW;
		this.column = DEFAULT_COLUMN;
	}
	
	public static Square getSize(int size) {
		return new Square(size, size);
	}
	
	public static Square getDefaultSize() {
		return new Square();
	}
}

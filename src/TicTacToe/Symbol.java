package TicTacToe;

/**
 * Created by bubblebitoey on 2/23/2017 AD.
 */
public enum Symbol {
	EMPTY, X, O;
	
	public String toString() {
		if (this == EMPTY) return "-";
		return name();
	}
}

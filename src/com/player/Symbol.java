package com.player;

/**
 * The enum that represent the symbol of the player.
 *
 * @author bubblebitoey
 * @since 2/23/2017 AD.
 */
public enum Symbol {
	/**
	 * this is the <b>empty</b> symbol which stand of nothing inside the location
	 */
	EMPTY, WIN, X, O;
	
	/**
	 * if the symbol is {@link #EMPTY} this method will return "-"; otherwise, return the string of variable (like <code>O</code> or <code>X</code>)
	 *
	 * @return <code>-</code> or the symbol look like.
	 */
	public String toString() {
		if (this == EMPTY) return "-";
		else if (this == WIN) return "[ ]";
		return name();
	}
}

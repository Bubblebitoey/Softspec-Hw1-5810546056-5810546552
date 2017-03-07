package com.model.player;

import java.util.*;
import java.util.stream.Stream;

/**
 * The enum that represent the symbol of each player. <br>
 * <b>Important</b>: to get all symbol in this class please use method {@link #getSymbols()} instant of {@link #values()}.
 *
 * @author bubblebitoey
 * @version 1.1
 * @since 2/23/2017 AD.
 */
public enum Symbol {
	/**
	 * this is the <b>empty</b> symbol which nothing inside the location
	 */
	EMPTY, WIN, X, O, A, Z, B, F, U, C, K;
	
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
	
	/**
	 * get all symbol but except {@link #EMPTY} and {@link #WIN} symbol
	 *
	 * @return all symbol
	 */
	public static Symbol[] getSymbols() {
		Stream<Symbol> stream = Arrays.stream(Symbol.values()).filter(symbol -> symbol != EMPTY && symbol != WIN);
		return stream.toArray(Symbol[]::new);
	}
}

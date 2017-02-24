package com.player;

/**
 * The game player. <br>
 * content:
 * <ul>
 * <li>name of the player</li>
 * <li>symbol that represent the player</li>
 * </ul>
 *
 * @author bubblebitoey
 * @since 2/23/2017 AD.
 */
public class Player {
	private final String name;
	private final Symbol s;
	
	public Player(String name, Symbol s) {
		this.name = name;
		this.s = s;
	}
	
	public String getName() {
		return name;
	}
	
	public Symbol getSymbol() {
		return s;
	}
	
	/**
	 * print the player name and symbol <br>
	 * Format:  <code>name(symbol)</code>
	 *
	 * @return the string to print out
	 */
	@Override
	public String toString() {
		return name + "(" + s + ")";
	}
}

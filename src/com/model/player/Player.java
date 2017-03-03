package com.model.player;

/**
 * The game player. <br>
 * content:
 * <ul>
 * <li>name of the player.</li>
 * <li>symbol that represent the player.</li>
 * </ul>
 *
 * @author bubblebitoey
 * @version 1.0.1
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
	 * print the player name and symbol. <br>
	 * Format:  <code>name(symbol).</code>
	 *
	 * @return the string to print out.
	 */
	@Override
	public String toString() {
		return name + "(" + s + ")";
	}
	
	/**
	 * check is same person or not. <br>
	 * Current doing: Check both Name and Symbol
	 *
	 * @param obj
	 * 		other object to compare.
	 * @return true, if equals; otherwise is false.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		Player newPlayer = (Player) obj;
		return this.name.equals(newPlayer.getName()) && this.getSymbol() == newPlayer.getSymbol();
	}
}

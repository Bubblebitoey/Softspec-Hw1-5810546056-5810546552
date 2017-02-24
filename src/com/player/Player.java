package com.player;

/**
 * Created by bubblebitoey on 2/23/2017 AD.
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
	
	@Override
	public String toString() {
		return name;
	}
}

package com.history;

import com.board.Board;
import com.player.Player;
import com.player.Position;

import java.util.*;

/**
 * @author kamontat
 * @version 1.0
 * @since Sat 25/Feb/2017 - 2:28 AM
 */
public class BoardHistory {
	private static BoardHistory ourInstance;
	private List<Triple> lists;
	
	public static BoardHistory getInstance() {
		if (ourInstance == null) ourInstance = new BoardHistory();
		return ourInstance;
	}
	
	private BoardHistory() {
		lists = new ArrayList<>();
	}
	
	public void addNewHistory(Board board, Player player, Position p) {
		lists.add(new Triple(board, player, p));
	}
	
	public List<Triple> getHistory() {
		return lists;
	}
	
	@Override
	public String toString() {
		String out = "";
		for (Triple t : lists) {
			//			out += String.format("player: %s %s \n%s", t.getPlayer(), t.getPosition(), t.getTime());
			out += String.format("%d %d", t.getPosition().x + 1, t.getPosition().y + 1);
			out += "\n";
		}
		return out;
	}
}

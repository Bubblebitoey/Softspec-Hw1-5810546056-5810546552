package com.model.history;

import com.model.board.Board;
import com.model.player.Location;
import com.model.player.Player;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author kamontat
 * @version 1.1
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
	
	public void addNewHistory(Board board, Player player, Location p) {
		lists.add(new Triple(board, player, p));
	}
	
	public List<Triple> getHistory() {
		return lists;
	}
	
	public void clearAllHistory() {
		lists.clear();
	}
	
	@Override
	public String toString() {
		String out = "";
		for (Triple t : lists) {
			out += String.format("player: %s %s \n%s", t.getPlayer(), t.getPosition(), t.getTime());
			
			// For debugging
			// out += String.format("%d %d", t.getPosition().row + 1, t.getPosition().col + 1);
			
			out += "\n";
		}
		return out;
	}
	
	/**
	 * @author kamontat
	 * @version 1.0
	 * @since Sat 25/Feb/2017 - 2:31 AM
	 */
	protected static class Triple {
		private Board board;
		private Player player;
		private LocalDateTime time;
		private Location position;
		
		protected Triple(Board board, Player player, Location position) {
			this.board = board;
			this.player = player;
			this.position = position;
			time = LocalDateTime.now();
		}
		
		public Board getBoard() {
			return board;
		}
		
		public Player getPlayer() {
			return player;
		}
		
		public LocalDateTime getTime() {
			return time;
		}
		
		public Location getPosition() {
			return position;
		}
	}
}

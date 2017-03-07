package com.model.history;

import com.model.board.Board;
import com.model.player.Location;
import com.model.player.Player;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

/**
 * history of the game board, and this class is Singleton class.
 *
 * @author kamontat
 * @version 1.1
 * @see java.io.Serializable to save to object state to file.
 * @since Sat 25/Feb/2017 - 2:28 AM
 */
public class BoardHistory implements Serializable {
	/**
	 * use serialVersionUID from JDK 1.0.2 for interoperability
	 */
	private static final long serialVersionUID = 1L;
	
	private static BoardHistory ourInstance;
	private List<Triple> lists;
	
	/**
	 * get instance.
	 *
	 * @return board history
	 */
	public static BoardHistory getInstance() {
		if (ourInstance == null) ourInstance = new BoardHistory();
		return ourInstance;
	}
	
	private BoardHistory() {
		lists = new ArrayList<>();
	}
	
	/**
	 * add new history by using board state, current player and the location that player place their symbol.
	 *
	 * @param board
	 * 		the playing board
	 * @param player
	 * 		the current player
	 * @param p
	 * 		the location that player place their symbol
	 */
	public void addNewHistory(Board board, Player player, Location p) {
		lists.add(new Triple(board, player, p));
	}
	
	public List<Triple> getHistory() {
		return lists;
	}
	
	/**
	 * clear all history
	 */
	public void clearAllHistory() {
		lists.clear();
	}
	
	@Override
	public String toString() {
		String out = "";
		for (Triple t : lists) {
			out += String.format("player: %s %s \n%s", t.getPlayer(), t.getLocation(), t.getTime());
			
			// For debugging
			// out += String.format("%d %d", t.getLocation().row + 1, t.getLocation().col + 1);
			
			out += "\n";
		}
		return out;
	}
	
	/**
	 * Use in Board history only <br>
	 * This contains board information, player and location of current symbol placed.
	 *
	 * @author kamontat
	 * @version 1.0
	 * @since Sat 25/Feb/2017 - 2:31 AM
	 */
	protected static class Triple {
		private Board board;
		private Player player;
		private LocalDateTime time;
		private Location location;
		
		protected Triple(Board board, Player player, Location location) {
			this.board = (Board) board.clone();
			this.player = new Player(player.getName(), player.getSymbol());
			this.location = (Location) location.clone();
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
		
		public Location getLocation() {
			return location;
		}
	}
}

package com.model.history;

import com.model.board.Board;
import com.model.player.Location;
import com.model.player.Player;

import java.time.LocalDateTime;

/**
 * @author kamontat
 * @version 1.0
 * @since Sat 25/Feb/2017 - 2:31 AM
 */
public class Triple {
	private Board board;
	private Player player;
	private LocalDateTime time;
	private Location position;
	
	Triple(Board board, Player player, Location position) {
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

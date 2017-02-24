package com.console;

import com.board.Board;
import com.player.Player;
import com.player.Position;

import java.io.InputStream;
import java.util.*;

/**
 * Created by bubblebitoey on 2/23/2017 AD.
 */
public class Game implements Runnable {
	public static final InputStream DEFAULT_INPUT_STREAM = System.in;
	
	private Scanner input;
	
	private Board board;
	private int currentPlayer;
	private Player[] players;
	
	public Game(Board board, Player... players) {
		input = new Scanner(DEFAULT_INPUT_STREAM);
		this.board = board;
		this.players = players;
	}
	
	public Game(InputStream s, Board board, Player... players) {
		input = new Scanner(s);
		
		this.board = board;
		this.players = players;
	}
	
	private Position input() {
		System.out.println(String.format("Player %s's turn.", getCurrentPlayer()));
		try {
			System.out.print("Please select row: ");
			int row = input.nextInt();
			
			System.out.print("Please select column: ");
			int col = input.nextInt();
			
			return new Position(row, col);
		} catch (InputMismatchException e) {
			return null;
		}
	}
	
	private void swapPlayer() {
		// next
		currentPlayer++;
		// if out of player length
		if (currentPlayer == players.length) currentPlayer = 0;
	}
	
	private Player getCurrentPlayer() {
		return players[currentPlayer];
	}
	
	@Override
	public void run() {
		System.out.println(board);
		while (board.state == Board.State.PLAYING) {
			Position p = input();
			if (p == null) System.out.println("Accepted only number");
			boolean isSuccess = board.insert(getCurrentPlayer(), p);
			if (isSuccess) {
				System.out.println(board);
				if (board.state == Board.State.PLAYING) swapPlayer();
			} else {
				System.out.println("Invalid row or column");
			}
		}
		
		if (board.state == Board.State.DRAW) {
			System.out.println("Draw.");
		} else if (board.state == Board.State.WIN) {
			System.out.println(board.winner + " winner.");
		}
	}
}

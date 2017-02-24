package TicTacToe.console;

import TicTacToe.board.Board;
import TicTacToe.player.Player;
import TicTacToe.player.Position;

import java.util.*;

/**
 * Created by bubblebitoey on 2/23/2017 AD.
 */
public class Game implements Runnable {
	private Board board;
	private int currentPlayer;
	private Player[] players;
	
	public Game(Board board, Player... players) {
		this.board = board;
		this.players = players;
	}
	
	private Position input() {
		Scanner input = new Scanner(System.in);
		
		System.out.println();
		System.out.println(String.format("Player %s(%s)'s turn.", getCurrentPlayer(), getCurrentPlayer().getSymbol()));
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
		while (board.state == Board.Condition.PLAYING) {
			Position p = input();
			if (p == null) System.out.println("Accepted only number");
			boolean isSuccess = board.insert(getCurrentPlayer(), p);
			if (isSuccess) {
				System.out.println(board);
				if (board.state == Board.Condition.PLAYING) swapPlayer();
			} else {
				System.out.println("Invalid row or column");
			}
		}
		
		if (board.state == Board.Condition.DRAW) {
			System.out.println("Draw.");
		} else if (board.state == Board.Condition.WIN) {
			System.out.println(board.winner + " winner.");
		}
	}
}

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
	private Player current;
	private Player player1;
	private Player player2;
	
	public Game(Board board, Player p1, Player p2) {
		this.board = board;
		player1 = current = p1;
		player2 = p2;
	}
	
	private Position input() {
		Scanner input = new Scanner(System.in);
		
		System.out.println();
		System.out.println(String.format("Player %s(%s)'s turn.", current, current.getSymbol()));
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
		if (current.equals(player1)) {
			current = player2;
		} else if (current.equals(player2)) {
			current = player1;
		}
	}
	
	@Override
	public void run() {
		System.out.println(board);
		while (board.state == Board.Condition.PLAYING) {
			Position p = input();
			if (p == null) System.out.println("Accepted only number");
			boolean isSuccess = board.insert(current, p);
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

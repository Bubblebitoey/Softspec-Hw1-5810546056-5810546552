package test;

import com.controller.GameBoard;
import com.model.player.Location;

import java.io.InputStream;
import java.util.*;

/**
 * This console will <b>not</b> print any result excepted draw or winner player. <br>
 * Please using in tester only.
 *
 * @author kamontat
 * @version 1.0
 * @since Fri 03/Mar/2017 - 1:04 AM
 */
public class TestConsole implements Runnable {
	
	private Scanner input;
	private GameBoard game;
	
	public TestConsole(InputStream inputStream, GameBoard game) {
		input = new Scanner(inputStream);
		this.game = game;
	}
	
	private Location input() {
		try {
			int row = input.nextInt();
			int col = input.nextInt();
			
			return new Location(row, col);
		} catch (InputMismatchException e) {
			input.nextLine();
			return null;
		} catch (NoSuchElementException e) {
			game.fail();
			throw e;
		}
	}
	
	@Override
	public void run() {
		while (game.getGameState() == GameBoard.State.PLAYING) {
			Location p = input();
			if (p != null) {
				boolean success = game.insert(p);
				if (success) {
					if (game.getGameState() == GameBoard.State.PLAYING) game.nextPlayer();
				}
			}
		}
		
		if (game.getGameState() == GameBoard.State.DRAW) {
			System.out.println("Draw.");
		} else if (game.getGameState() == GameBoard.State.WIN) {
			System.out.println(game.getWinner() + " winner.");
		}
	}
}

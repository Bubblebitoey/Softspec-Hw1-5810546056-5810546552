package test;

import com.board.Board;
import com.board.shape.Shape;
import com.board.shape.Square;
import com.console.Console;
import com.controller.GameBoard;
import com.controller.OXGame;
import com.player.Location;
import com.player.Player;
import com.player.Symbol;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author kamontat
 * @version 2.0
 * @since Fri 24/Feb/2017 - 11:39 PM
 */
public class Game5x5Tester {
	private final String folder = "testfile/_5x5/";
	private final Shape s = Square.getSize(5);
	
	private Board b;
	private GameBoard g;
	
	private Player p1 = new Player("p1", Symbol.O);
	private Player p2 = new Player("p2", Symbol.X);
	
	private InputStream reader(String fileName) {
		return Game5x5Tester.class.getResourceAsStream(folder + "/" + fileName);
	}
	
	@Before
	public void setBoard() {
		b = new Board(s);
		g = new OXGame(b, p1, p2);
	}
	
	@Test
	public void drawTester() throws IOException {
		final String testingFile = "draw.txt";
		InputStream s = reader(testingFile);
		
		Console c = new Console(s, g);
		
		try {
			c.run();
		} catch (NoSuchElementException e) {
			Assert.fail("Game Must End when read file done");
		}
		Assert.assertEquals(p1.getSymbol(), b.getSymbol(new Location(1, 1)));
		Assert.assertEquals(p1.getSymbol(), b.getSymbol(new Location(3, 2)));
		Assert.assertEquals(p2.getSymbol(), b.getSymbol(new Location(5, 5)));
		
		Assert.assertTrue(b.isFull());
		Assert.assertEquals(GameBoard.State.DRAW, g.getGameState());
	}
	
	@Test
	public void dLTester() throws IOException {
		final String testingFile = "dia_l.txt";
		InputStream s = reader(testingFile);
		
		Console c = new Console(s, g);
		try {
			c.run();
		} catch (NoSuchElementException e) {
			Assert.fail("Game Must End when read file done");
		}
		
		Assert.assertEquals(p1.getSymbol(), b.getSymbol(new Location(1, 1)));
		Assert.assertEquals(p2.getSymbol(), b.getSymbol(new Location(4, 3)));
		Assert.assertEquals(p1.getSymbol(), b.getSymbol(new Location(1, 4)));
		
		Assert.assertEquals(GameBoard.State.WIN, g.getGameState());
		Assert.assertTrue(g.getWinner().equals(p1));
	}
	
	@Test
	public void dRTester() throws IOException {
		final String testingFile = "dia_r.txt";
		InputStream s = reader(testingFile);
		
		Console c = new Console(s, g);
		try {
			c.run();
		} catch (NoSuchElementException e) {
			Assert.fail("Game Must End when read file done");
		}
		
		Assert.assertEquals(p2.getSymbol(), b.getSymbol(new Location(1, 5)));
		Assert.assertEquals(p2.getSymbol(), b.getSymbol(new Location(3, 3)));
		Assert.assertEquals(p2.getSymbol(), b.getSymbol(new Location(4, 2)));
		Assert.assertEquals(p2.getSymbol(), b.getSymbol(new Location(5, 1)));
		
		Assert.assertEquals(Symbol.WIN, b.getSymbol(new Location(2, 4)));
		
		Assert.assertEquals(GameBoard.State.WIN, g.getGameState());
		Assert.assertTrue(g.getWinner().equals(p2));
	}
}
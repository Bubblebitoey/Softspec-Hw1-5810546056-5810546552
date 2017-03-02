package test;

import com.board.Board;
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
public class Game9x9Tester {
	private final String folder = "testfile/_9x9/";
	
	private Board b;
	private GameBoard g;
	
	private Player p1 = new Player("p1", Symbol.O);
	private Player p2 = new Player("p2", Symbol.X);
	
	private InputStream reader(String fileName) {
		return Game9x9Tester.class.getResourceAsStream(folder + "/" + fileName);
	}
	
	@Before
	public void setBoard() {
		b = new Board(Square.getDefaultSize());
		g = new OXGame(b, p1, p2);
	}
	
	@Test
	public void rowTester() throws IOException {
		final String testingFile = "row.txt";
		InputStream s = reader(testingFile);
		
		Console c = new Console(s, g);
		try {
			c.run();
		} catch (NoSuchElementException e) {
			Assert.fail("Game Must End when read file done");
		}
		
		Assert.assertEquals(p2.getSymbol(), b.getSymbol(new Location(1, 9)));
		Assert.assertEquals(p2.getSymbol(), b.getSymbol(new Location(1, 1)));
		
		Assert.assertTrue(g.getWinner().equals(p2));
	}
	
	@Test
	public void colTester() throws IOException {
		final String testingFile = "col.txt";
		InputStream s = reader(testingFile);
		
		Console c = new Console(s, g);
		try {
			c.run();
		} catch (NoSuchElementException e) {
			Assert.fail("Game Must End when read file done");
		}
		Symbol getSymbol = b.getSymbol(new Location(1, 2));
		
		Assert.assertEquals(p2.getSymbol(), getSymbol);
		Assert.assertFalse(b.isFull());
		
		Assert.assertTrue(g.getWinner().equals(p1));
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
		
		Assert.assertFalse(b.isFull());
		
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
		Assert.assertEquals(p1.getSymbol(), b.getSymbol(new Location(7, 7)));
		Assert.assertEquals(p1.getSymbol(), b.getSymbol(new Location(6, 9)));
		
		Assert.assertTrue(b.isEmpty(new Location(5, 1)));
		Assert.assertFalse(b.isFull());
		
		Assert.assertTrue(g.getWinner().equals(p1));
	}
}
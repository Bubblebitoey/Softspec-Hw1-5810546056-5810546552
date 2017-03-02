package test;

import com.board.Board;
import com.board.shape.Shape;
import com.board.shape.Square;
import com.console.Console;
import com.controller.OXGame;
import com.player.Location;
import com.player.Player;
import com.player.Symbol;
import com.strategy.DiagonalStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author kamontat
 * @version 1.0
 * @since Fri 24/Feb/2017 - 11:39 PM
 */
public class Game30x30Tester {
	private final String folder = "testfile/_30x30/";
	private final Shape s = Square.getSize(30);
	private final int winCond = 10;
	
	private Board b;
	
	private Player p1 = new Player("p1", Symbol.O);
	private Player p2 = new Player("p2", Symbol.X);
	
	private OXGame g = new OXGame(b, p1, p2);
	
	private InputStream reader(String fileName) {
		return Game30x30Tester.class.getResourceAsStream(folder + "/" + fileName);
	}
	
	@Before
	public void setBoard() {
		b = new Board(s, winCond);
	}
	
	@Test
	public void colTester() throws IOException {
		final String testingFile = "col.txt";
		InputStream s = reader(testingFile);
		
		Console c = new Console(s, g);
		try {
			c.play();
		} catch (NoSuchElementException e) {
			Assert.fail("Game Must End when read file done");
		}
		
		Assert.assertEquals(p1.getSymbol(), b.getSymbol(new Location(17, 6)));
		Assert.assertEquals(p2.getSymbol(), b.getSymbol(new Location(25, 27)));
		
		Assert.assertTrue(b.isEmpty(new Location(14, 4)));
		Assert.assertFalse(b.isBoardFull());
		
		Assert.assertTrue(b.winner.equals(p1));
	}
	
	@Test
	public void rowTester() throws IOException {
		final String testingFile = "row.txt";
		InputStream s = reader(testingFile);
		
		Console c = new Console(s, g);
		try {
			c.play();
		} catch (NoSuchElementException e) {
			Assert.fail("Game Must End when read file done");
		}
		
		String old = b.toString();
		
		Assert.assertTrue(b.insert(p1, new Location(28, 1)));
		Assert.assertTrue(b.insert(p1, new Location(24, 13)));
		
		Assert.assertFalse(b.insert(p2, new Location(30, 30)));
		Assert.assertFalse(b.insert(p2, new Location(10, 10)));
		
		Assert.assertNotEquals(old, b.toString());
		
		Assert.assertFalse(b.isBoardFull());
		
		Assert.assertTrue(b.winner.equals(p1));
	}
	
	@Test
	public void dLTester() throws IOException {
		final String testingFile = "dia_l.txt";
		InputStream s = reader(testingFile);
		
		Console c = new Console(s, g);
		try {
			c.play();
		} catch (NoSuchElementException e) {
			Assert.fail("Game Must End when read file done");
		}
		
		Assert.assertEquals(Symbol.WIN, b.getSymbol(new Location(17, 22)));
		Assert.assertFalse(b.isBoardFull());
		Assert.assertTrue(b.winner.equals(p2));
	}
	
	@Test
	public void dRTester() throws IOException {
		final String testingFile = "dia_r.txt";
		InputStream s = reader(testingFile);
		
		Console c = new Console(s, g);
		try {
			c.play();
		} catch (NoSuchElementException e) {
			Assert.fail("Game Must End when read file done");
		}
		
		String win = "";
		for (int i = 0; i < winCond; i++) {
			win += p2.getSymbol();
		}
		
		Assert.assertTrue(new DiagonalStrategy(b).execute(new Location(11, 15), win));
	}
}
package test.subtester;

import com.model.board.Board;
import com.model.board.shape.Square;
import com.controller.GameBoard;
import com.controller.OXGame;
import com.model.player.Location;
import com.model.player.Player;
import com.model.player.Symbol;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.TestConsole;
import test.Tester;

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
	
	private Board b = new Board(Square.getDefaultSize());
	
	private Player p1 = new Player("p1", Symbol.O);
	private Player p2 = new Player("p2", Symbol.X);
	
	private GameBoard game = new OXGame(b, p1, p2);
	
	private InputStream reader(String fileName) {
		return Tester.class.getResourceAsStream(folder + "/" + fileName);
	}
	
	@Before
	public void restart() {
		game.restart();
	}
	
	@Test
	public void rowTester() throws IOException {
		final String testingFile = "row.txt";
		InputStream s = reader(testingFile);
		
		try {
			new TestConsole(s, game).run();
		} catch (NoSuchElementException e) {
			Assert.fail("Game Must End when read file done");
		}
		
		Assert.assertEquals(p1.getSymbol(), b.getSymbol(new Location(1, 5)));
		Assert.assertEquals(Symbol.WIN, b.getSymbol(new Location(1, 6)));
		Assert.assertEquals(p1.getSymbol(), b.getSymbol(new Location(1, 7)));
		Assert.assertEquals(p1.getSymbol(), b.getSymbol(new Location(1, 8)));
		Assert.assertEquals(p1.getSymbol(), b.getSymbol(new Location(1, 9)));
		
		Assert.assertTrue(game.getWinner().equals(p1));
	}
	
	@Test
	public void colTester() throws IOException {
		final String testingFile = "col.txt";
		InputStream s = reader(testingFile);
		
		try {
			new TestConsole(s, game).run();
		} catch (NoSuchElementException e) {
			Assert.fail("Game Must End when read file done");
		}
		
		Assert.assertEquals(p1.getSymbol(), b.getSymbol(new Location(1, 2)));
		Assert.assertFalse(b.isFull());
		
		Assert.assertTrue(game.getWinner().equals(p2));
	}
	
	@Test
	public void dLTester() throws IOException {
		final String testingFile = "dia_l.txt";
		InputStream s = reader(testingFile);
		
		try {
			new TestConsole(s, game).run();
		} catch (NoSuchElementException e) {
			Assert.fail("Game Must End when read file done");
		}
		
		Assert.assertFalse(b.isFull());
		
		Assert.assertTrue(game.getWinner().equals(p2));
	}
	
	@Test
	public void dRTester() throws IOException {
		final String testingFile = "dia_r.txt";
		InputStream s = reader(testingFile);
		
		try {
			new TestConsole(s, game).run();
		} catch (NoSuchElementException e) {
			Assert.fail("Game Must End when read file done");
		}
		Assert.assertEquals(p2.getSymbol(), b.getSymbol(new Location(7, 7)));
		Assert.assertEquals(p2.getSymbol(), b.getSymbol(new Location(6, 9)));
		
		Assert.assertTrue(b.isEmpty(new Location(5, 1)));
		Assert.assertFalse(b.isFull());
		
		Assert.assertTrue(game.getWinner().equals(p2));
	}
}
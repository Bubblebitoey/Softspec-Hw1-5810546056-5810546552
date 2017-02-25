package test;

import com.board.Board;
import com.board.shape.Shape;
import com.board.shape.Square;
import com.console.OXGame;
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
 * @version 1.0
 * @since Fri 24/Feb/2017 - 11:39 PM
 */
public class Game30x30Tester {
	private final String folder = "testfile/_30x30/";
	private final Shape s = Square.getSize(30);
	
	private Board b;
	
	private Player p1 = new Player("p1", Symbol.O);
	private Player p2 = new Player("p2", Symbol.X);
	
	private InputStream reader(String fileName) {
		return Game30x30Tester.class.getResourceAsStream(folder + "/" + fileName);
	}
	
	@Before
	public void setBoard() {
		b = new Board(s, 9);
	}
	
	@Test
	public void colTester() throws IOException {
		final String testingFile = "col.txt";
		InputStream s = reader(testingFile);
		
		OXGame g = new OXGame(s, b, p1, p2);
		try {
			g.run();
		} catch (NoSuchElementException e) {
			Assert.fail("Game Must End when read file done");
		}
		
		// TODO 2/25/2017 AD 11:24 PM add more test
	}
}
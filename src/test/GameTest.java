package test;

import com.board.Board;
import com.board.shape.Square;
import com.console.OXGame;
import com.player.Player;
import com.player.Symbol;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author kamontat
 * @version 1.0
 * @since Fri 24/Feb/2017 - 11:39 PM
 */
public class GameTest {
	private final String folder = "testfile/";
	
	private Player p1 = new Player("p1", Symbol.O);
	private Player p2 = new Player("p2", Symbol.X);
	
	private InputStream reader(String fileName) {
		return TestClass.class.getResourceAsStream(folder + "/" + fileName);
	}
	
	@Test
	public void row9x9Tester() throws IOException {
		final String testingFile = "in_row_9x9.txt";
		final Board b = new Board(Square.getDefaultSize());
		
		// read file
		InputStream s = reader(testingFile);
		
		OXGame g = new OXGame(s, b, p1, p2);
		try {
			g.run();
		} catch (NoSuchElementException e) {
			Assert.fail("Game Must End when read file done");
		}
	}
}
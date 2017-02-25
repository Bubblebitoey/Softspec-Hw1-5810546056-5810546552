package test;

import com.board.Board;
import com.board.shape.Shape;
import com.board.shape.Square;
import com.console.OXGame;
import com.player.Player;
import com.player.Position;
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
public class Game5x5Tester {
	private final String folder = "testfile/_5x5/";
	private final Shape s = Square.getSize(5);
	
	private Board b;
	
	private Player p1 = new Player("p1", Symbol.O);
	private Player p2 = new Player("p2", Symbol.X);
	
	private InputStream reader(String fileName) {
		return Game5x5Tester.class.getResourceAsStream(folder + "/" + fileName);
	}
	
	@Before
	public void setBoard() {
		b = new Board(s);
		System.out.println("setting new board");
	}
	
	@Test
	public void DrawTester() throws IOException {
		final String testingFile = "draw.txt";
		InputStream s = reader(testingFile);
		
		OXGame g = new OXGame(s, b, p1, p2);
		try {
			g.run();
		} catch (NoSuchElementException e) {
			Assert.fail("Game Must End when read file done");
		}
		Assert.assertEquals(p1.getSymbol(), b.getSymbol(new Position(1, 1)));
		Assert.assertEquals(p1.getSymbol(), b.getSymbol(new Position(3, 2)));
		Assert.assertEquals(p2.getSymbol(), b.getSymbol(new Position(5, 5)));
		
		Assert.assertTrue(b.isBoardFull());
		Assert.assertEquals(Board.State.DRAW, b.state);
		
		
	}
}
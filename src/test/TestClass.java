package test;

import com.board.Board;
import com.board.shape.Square;
import com.console.Game;
import com.player.Player;
import com.player.Symbol;

import java.io.*;
import java.util.*;

/**
 * @author kamontat
 * @version 1.0
 * @since Fri 24/Feb/2017 - 11:03 PM
 */
public class TestClass {
	private static final String testFolder = "testfile";
	private static final InputStream testFilesName = TestClass.class.getResourceAsStream(testFolder);
	
	private static final Board b = new Board(Square.getDefaultSize());
	
	private static final Player p1 = new Player("p1", Symbol.O);
	private static final Player p2 = new Player("p2", Symbol.X);
	
	public static void main(String[] args) throws IOException {
		BufferedReader readerFileName = new BufferedReader(new InputStreamReader(testFilesName));
		String fileName = "";
		while ((fileName = readerFileName.readLine()) != null) {
			InputStream s = TestClass.class.getResourceAsStream(testFolder + "/" + fileName);
			
			Game g = new Game(s, b, p1, p2);
			try {
				g.run();
			} catch (NoSuchElementException e) {
				System.out.println("FAIL");
			}
		}
	}
}

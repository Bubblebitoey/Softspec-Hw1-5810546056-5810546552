package com.view.gui;

import com.controller.GameBoard;
import com.controller.OXGame;
import com.model.board.Board;
import com.model.board.shape.Shape;
import com.model.implementation.Pair;
import com.model.player.Player;
import com.model.player.Symbol;
import com.view.gui.lib.Parser;
import com.view.gui.panel.InputPanel;
import com.view.gui.panel.SymbolPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author kamontat
 * @version 1.0
 * @since Fri 03/Mar/2017 - 9:42 PM
 */
public class SettingPage extends JFrame implements Runnable {
	private static final String row = "Row: ";
	private static final String col = "Column: ";
	private static final String consecutive = "Win Consecutive: ";
	private static final String name = "Name: ";
	private static final String symbol = "Symbol: ";
	
	private JPanel pane = new JPanel();
	
	
	public SettingPage() {
		super("Setup Page");
		
		createUIComponents();
		setContentPane(pane);
	}
	
	@Override
	public void run() {
		pack();
		setMinimumSize(this.getSize());
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	private void createUIComponents() {
		Window self = this;
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		InputPanel boardSetting = new InputPanel("Board Size", row, col, consecutive);
		boardSetting.getComponent(consecutive, JTextField.class).setToolTipText("default is 5 (Optional)");
		
		pane.add(boardSetting);
		
		InputPanel player1Setting = new InputPanel("Player1", Pair.create(name, new JTextField(10)), Pair.create(symbol, new SymbolPanel()));
		pane.add(player1Setting);
		
		InputPanel player2Setting = new InputPanel("Player2", Pair.create(name, new JTextField(10)), Pair.create(symbol, new SymbolPanel()));
		pane.add(player2Setting);
		
		JButton b = new JButton("OK");
		getRootPane().setDefaultButton(b);
		b.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer r = Parser.getParsable().parseTo(boardSetting.getComponent(row, JTextField.class).getText(), Integer.class);
				Integer c = Parser.getParsable().parseTo(boardSetting.getComponent(col, JTextField.class).getText(), Integer.class);
				Integer con = Parser.getParsable().parseTo(boardSetting.getComponent(consecutive, JTextField.class).getText(), Integer.class);
				
				if (r == null || c == null) {
					JOptionPane.showMessageDialog(null, "row and column are unacceptable.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Board b;
				try {
					b = new Board(com.model.board.shape.Rectangle.getSize(r, c), con == null ? 5: con);
				} catch (Shape.NegativeShapeSize negativeShapeSize) {
					JOptionPane.showMessageDialog(null, "row and column cannot be negative.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				String pName = player1Setting.getComponent(name, JTextField.class).getText();
				Symbol pSymbol = player1Setting.getComponent(symbol, SymbolPanel.class).getSelected();
				Player p1 = new Player(pName, pSymbol);
				
				pName = player2Setting.getComponent(name, JTextField.class).getText();
				pSymbol = player2Setting.getComponent(symbol, SymbolPanel.class).getSelected();
				Player p2 = new Player(pName, pSymbol);
				
				if (p1.equals(p2)) {
					JOptionPane.showMessageDialog(null, "Both name and Symbol should unique.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				GameBoard g = new OXGame(b, p1, p2);
				SwingUtilities.invokeLater(new MainPage(self, g));
			}
		});
		pane.add(b);
	}
}

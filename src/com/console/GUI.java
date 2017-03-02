package com.console;

import com.controller.GameBoard;
import com.player.Location;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

/**
 * Created by bubblebitoey on 3/2/2017 AD.
 */
public class GUI extends JFrame implements Runnable {
	private static final Dimension DEFAULT_SIZE = new Dimension(350, 350);
	private JPanel pane = new JPanel();
	
	private GameBoard game;
	
	public GUI(GameBoard game) {
		super("GUI");
		this.game = game;
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
		JTable table = new JTable(game.getSize().getRow(), game.getSize().getColumn()) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.setGridColor(Color.BLACK);
		
		table.setDragEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		table.setFont(new Font(null, Font.BOLD, 20));
		
		table.setMinimumSize(DEFAULT_SIZE);
		table.setRowHeight(DEFAULT_SIZE.height / game.getSize().getColumn());
		Enumeration<TableColumn> columns = table.getColumnModel().getColumns();
		while (columns.hasMoreElements()) {
			TableColumn c = columns.nextElement();
			c.setPreferredWidth(DEFAULT_SIZE.width / game.getSize().getRow());
		}
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				int col = table.columnAtPoint(e.getPoint());
				play(table, new Location(row + 1, col + 1));
			}
		});
		pane.add(table);
	}
	
	private void play(JTable table, Location l) {
		if (game.getGameState() == GameBoard.State.PLAYING) {
			boolean success = game.insert(l);
			if (success) {
				table.setValueAt(game.currentPlayer().getSymbol(), l.row, l.col);
				if (game.getGameState() == GameBoard.State.PLAYING) game.nextPlayer();
			}
		}
		
		if (game.getGameState() == GameBoard.State.DRAW) {
			System.out.println("draw");
		} else if (game.getGameState() == GameBoard.State.WIN) {
			int result = JOptionPane.showConfirmDialog(this, "Do you want to play again?", game.getWinner() + " winner.", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				System.out.println("play again.");
			} else {
				System.out.println("done.");
			}
		}
	}
}

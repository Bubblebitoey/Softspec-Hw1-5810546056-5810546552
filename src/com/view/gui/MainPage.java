package com.view.gui;

import com.controller.GameBoard;
import com.model.player.Location;
import com.view.gui.panel.OptionPanel;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

/**
 * @author bubblebitoey
 * @version 1.2
 * @since 3/2/2017 AD.
 */
public class MainPage extends JFrame implements Runnable {
	private static final Dimension DEFAULT_SIZE = new Dimension(350, 350);
	private JPanel pane = new JPanel();
	private OptionPanel optionPanel = new OptionPanel();
	private JTable table;
	private JLabel nameLb = new JLabel("Current player: name");
	
	private GameBoard game;
	
	public MainPage(GameBoard game) {
		super("MainPage");
		this.game = game;
		// update current player name
		updateCurrentPlayer();
		
		initRestartPanel();
		initTable();
		
		createUIComponents();
		setContentPane(pane);
	}
	
	
	@Override
	public void run() {
		game.start();
		
		pack();
		setMinimumSize(this.getSize());
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	private void createUIComponents() {
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		JPanel north = new JPanel();
		north.setLayout(new BoxLayout(north, BoxLayout.LINE_AXIS));
		north.add(nameLb);
		
		pane.add(north);
		pane.add(table);
		pane.add(optionPanel);
	}
	
	private void initRestartPanel() {
		optionPanel.add("Restart", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				restart();
			}
		});
		optionPanel.add("Exit", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		optionPanel.invisible(this);
	}
	
	private void initTable() {
		table = new JTable(game.getSize().getRow(), game.getSize().getColumn()) {
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
			public void mousePressed(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				int col = table.columnAtPoint(e.getPoint());
				play(table, new Location(row + 1, col + 1));
			}
		});
	}
	
	/**
	 * remove all data in table
	 *
	 * @param table
	 * 		the removed data table
	 */
	private void removeAll(final JTable table) {
		for (int i = 0; i < table.getRowCount(); i++) {
			for (int j = 0; j < table.getColumnCount(); j++) {
				table.setValueAt("", i, j);
			}
		}
	}
	
	private void updateCurrentPlayer() {
		nameLb.setText("Current player: " + game.currentPlayer());
	}
	
	private void play(JTable table, Location l) {
		if (game.getGameState() == GameBoard.State.PLAYING) {
			boolean success = game.insert(l);
			if (success) {
				table.setValueAt(game.currentPlayer().getSymbol(), l.row, l.col);
				if (game.getGameState() == GameBoard.State.PLAYING) game.nextPlayer();
				// update name
				updateCurrentPlayer();
			}
		}
		
		String message = "";
		
		if (game.getGameState() == GameBoard.State.DRAW) {
			nameLb.setText("Draw!");
		} else if (game.getGameState() == GameBoard.State.WIN) {
			nameLb.setText("Winner player: " + game.getWinner());
		}
		
		if (game.getGameState() != GameBoard.State.PLAYING && game.getGameState() != GameBoard.State.END) {
			optionPanel.visible(this);
			// south.setVisible(true);
			//			int result = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Message", JOptionPane.YES_NO_OPTION);
			//			if (result == JOptionPane.YES_OPTION) {
			//				game.restart();
			//				removeAll(table);
			//			} else {
			//				game.end();
			//			}
		}
	}
	
	private void restart() {
		game.restart();
		removeAll(table);
		updateCurrentPlayer();
		optionPanel.invisible(this);
	}
}
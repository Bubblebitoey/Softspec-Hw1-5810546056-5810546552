package com.view.gui;

import com.controller.GameBoard;
import com.model.player.Location;
import com.view.gui.lib.DimensionUtilities;
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
public class MainPage extends JDialog implements Runnable {
	private static final int BLOCK_SIZE = 25;
	private JPanel pane = new JPanel();
	private OptionPanel optionPanel = new OptionPanel();
	private JTable table;
	private JLabel nameLb = new JLabel("Current player: name");
	
	private GameBoard game;
	
	public MainPage(Window owner, GameBoard game) {
		super(owner, "Game Page");
		
		setModal(true);
		setLocation(new Point(owner.getLocation().x + owner.getSize().width, owner.getLocation().y));
		
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
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	private void createUIComponents() {
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		JPanel north = new JPanel();
		north.setLayout(new BoxLayout(north, BoxLayout.LINE_AXIS));
		
		nameLb.setFont(new Font("", Font.BOLD, 21));
		north.add(nameLb);
		
		pane.add(north);
		
		pane.add(new JScrollPane(table));
		pane.add(optionPanel);
	}
	
	private void initRestartPanel() {
		optionPanel.add("New Game!", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newGame();
			}
		});
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
	}
	
	private void initTable() {
		table = new JTable(game.getSize().height, game.getSize().width) {
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
		table.setTableHeader(null);
		
		
		table.setFont(new Font(null, Font.BOLD, 20));
		
		Dimension gameSize = new Dimension(BLOCK_SIZE, BLOCK_SIZE);
		
		Dimension tablePreferredSize = DimensionUtilities.operation(table.getPreferredScrollableViewportSize(), DimensionUtilities.Operation.DIVIDE, game.getSize());
		
		Dimension preferredSize = DimensionUtilities.maximum(gameSize, tablePreferredSize);
		
		table.setRowHeight(preferredSize.height);
		Enumeration<TableColumn> columns = table.getColumnModel().getColumns();
		while (columns.hasMoreElements()) {
			TableColumn c = columns.nextElement();
			c.setPreferredWidth(preferredSize.width);
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
			nameLb.setForeground(Color.BLUE);
			nameLb.setText("Draw!");
		} else if (game.getGameState() == GameBoard.State.WIN) {
			nameLb.setForeground(Color.RED);
			nameLb.setText("Winner player: " + game.getWinner());
		}
		
		if (game.getGameState() != GameBoard.State.PLAYING && game.getGameState() != GameBoard.State.END) {
			// TODO 3/6/2017 AD 10:56 PM do some thing when game end.
		}
	}
	
	private void restart() {
		game.restart();
		removeAll(table);
		updateCurrentPlayer();
		nameLb.setForeground(Color.BLACK);
	}
	
	private void newGame() {
		nameLb.setForeground(Color.BLACK);
		dispose();
	}
}

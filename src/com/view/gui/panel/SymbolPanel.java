package com.view.gui.panel;

import com.model.player.Symbol;

import javax.swing.*;

/**
 * @author kamontat
 * @version 1.0
 * @since Mon 06/Mar/2017 - 10:42 PM
 */
public class SymbolPanel extends JPanel {
	private JComboBox<Symbol> comboBox;
	
	public SymbolPanel() {
		comboBox = new JComboBox<Symbol>(Symbol.getSymbols());
		add(comboBox);
	}
	
	public Symbol getSelected() {
		return (Symbol) comboBox.getSelectedItem();
	}
}

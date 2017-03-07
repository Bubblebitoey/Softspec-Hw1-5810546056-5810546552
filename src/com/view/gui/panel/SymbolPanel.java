package com.view.gui.panel;

import com.model.player.Symbol;

import javax.swing.*;

/**
 * symbol combobox that will get all valid symbol and create the commobox of them.
 *
 * @author kamontat
 * @version 1.0
 * @since Mon 06/Mar/2017 - 10:42 PM
 */
public class SymbolPanel extends JPanel {
	private JComboBox<Symbol> comboBox;
	
	/**
	 * create new panel
	 */
	public SymbolPanel() {
		comboBox = new JComboBox<Symbol>(Symbol.getSymbols());
		add(comboBox);
	}
	
	/**
	 * get current selected symbol
	 *
	 * @return selected symbol
	 */
	public Symbol getSelected() {
		return (Symbol) comboBox.getSelectedItem();
	}
}

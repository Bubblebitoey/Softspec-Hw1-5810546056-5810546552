package com.view.gui.panel;

import com.view.gui.lib.SpringUtilities;

import javax.swing.*;
import java.util.*;

/**
 * @author kamontat
 * @version 1.0
 * @since Mon 06/Mar/2017 - 6:52 PM
 */
public class InputPanel extends JPanel {
	private Map<String, JTextField> map;
	
	/**
	 * @param header
	 * 		the header of panel
	 * @param labels
	 * 		can't be same (should be unique)
	 */
	public InputPanel(String header, String... labels) {
		map = new HashMap<>(labels.length);
		
		setLayout(new SpringLayout());
		setHeader(header);
		
		for (String label : labels) {
			JLabel l = new JLabel(label, SwingConstants.TRAILING);
			add(l);
			JTextField textField = new JTextField(10);
			l.setLabelFor(textField);
			add(textField);
			
			// save
			map.put(label, textField);
		}
		
		//Lay out the panel.
		SpringUtilities.makeCompactGrid(this, labels.length + 1, 2, 6, 6, 6, 6);
		
		setOpaque(true);
	}
	
	private void setHeader(String header) {
		add(new JLabel(header, SwingConstants.LEADING));
		add(new JLabel(""));
	}
	
	public void addActionListener(String key, Action action) {
		map.get(key).addActionListener(action);
	}
}

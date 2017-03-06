package com.view.gui.panel;

import com.model.implementation.Pair;
import com.view.gui.lib.SpringUtilities;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * @author kamontat
 * @version 1.0
 * @since Mon 06/Mar/2017 - 6:52 PM
 */
public class InputPanel extends JPanel {
	private Map<String, JComponent> map;
	
	/**
	 * @param header
	 * 		the header of panel
	 * @param keyLabel
	 * 		can't be same (should be unique)
	 */
	public InputPanel(String header, String... keyLabel) {
		map = new HashMap<>(keyLabel.length);
		setLayout(new SpringLayout());
		setHeader(header);
		
		for (String label : keyLabel) {
			JLabel l = new JLabel(label, SwingConstants.TRAILING);
			add(l);
			JTextField textField = new JTextField(10);
			// l.setLabelFor(textField);
			add(textField);
			
			// save
			map.put(label, textField);
		}
		
		//Lay out the panel.
		SpringUtilities.makeCompactGrid(this, keyLabel.length + 1, 2, 6, 6, 6, 6);
		setOpaque(true);
	}
	
	
	@SafeVarargs
	public InputPanel(String header, Pair<String, ? extends JComponent>... pairs) {
		map = new HashMap<>(pairs.length);
		
		setLayout(new SpringLayout());
		setHeader(header);
		
		for (Pair<String, ? extends JComponent> pair : pairs) {
			JLabel l = new JLabel(pair.getKey(), SwingConstants.TRAILING);
			add(l);
			add(pair.getValue());
			l.setLabelFor(pair.getValue());
			// save
			map.put(pair.getKey(), pair.getValue());
		}
		
		//Lay out the panel.
		SpringUtilities.makeCompactGrid(this, pairs.length + 1, 2, 6, 6, 6, 6);
		setOpaque(true);
	}
	
	private void setHeader(String header) {
		JLabel headLb = new JLabel(header, SwingConstants.LEADING);
		headLb.setFont(new Font(null, Font.BOLD, 15));
		add(headLb);
		add(new JLabel(""));
	}
	
	/**
	 * This method will return value in <code>T</code> Class, please <b>make sure</b> that <code>key</code> contains some type of <code>JComponent</code>
	 *
	 * @param key
	 * 		the key (label)
	 * @return T class that input or null
	 */
	public <T extends JComponent> T getComponent(String key, Class<T> aClass) {
		try {
			return aClass.cast(map.get(key));
		} catch (ClassCastException e) {
			return null;
		}
	}
}

package com.view.gui.panel;

import javax.swing.*;
import java.awt.*;

/**
 * @author kamontat
 * @version 1.0
 * @since Fri 03/Mar/2017 - 9:28 PM
 */
public class OptionPanel extends JPanel {
	public OptionPanel() {
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
	}
	
	public void add(String buttonName, Action ac) {
		JButton b = new JButton(buttonName);
		b.addActionListener(ac);
		
		add(b);
	}
	
	public void invisible(Window window) {
		setVisible(false);
		window.pack();
	}
	
	public void visible(Window window) {
		setVisible(true);
		window.pack();
	}
}

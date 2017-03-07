package com.view.gui.panel;

import javax.swing.*;
import java.awt.*;

/**
 * option panel, in line layout
 * <p>
 * |button|...
 *
 * @author kamontat
 * @version 1.0
 * @since Fri 03/Mar/2017 - 9:28 PM
 */
public class OptionPanel extends JPanel {
	/**
	 * create panel.
	 */
	public OptionPanel() {
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
	}
	
	/**
	 * add new button.
	 *
	 * @param buttonName
	 * 		name of button.
	 * @param ac
	 * 		action when button been press.
	 */
	public void add(String buttonName, Action ac) {
		JButton b = new JButton(buttonName);
		b.addActionListener(ac);
		
		add(b);
	}
	
	/**
	 * set panel <b>invisible</b>.
	 *
	 * @param window
	 * 		repack this window
	 */
	public void invisible(Window window) {
		setVisible(false);
		window.pack();
	}
	
	/**
	 * set panel <b>visible</b>.
	 *
	 * @param window
	 * 		repack this window
	 */
	public void visible(Window window) {
		setVisible(true);
		window.pack();
	}
}

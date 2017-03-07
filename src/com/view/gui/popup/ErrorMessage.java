package com.view.gui.popup;

import javax.swing.*;

/**
 * getting error popup using {@link JOptionPane}.
 *
 * @author kamontat
 * @version 1.0
 * @since Tue 07/Mar/2017 - 2:41 PM
 */
public class ErrorMessage {
	/**
	 * get error popup.
	 *
	 * @param e
	 * 		error message.
	 */
	public static void get(String e) {
		JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
	}
}

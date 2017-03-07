package com.view.gui.lib;

import java.awt.*;

/**
 * get {@link Point} in the screen with some condition.
 *
 * @author kamontat
 * @version 4.1
 * @since 17/8/59 - 23:54
 */
public class Display {
	/**
	 * get default point at x=0, y=0
	 */
	public static Point getDefaultPoint = new Point(0, 0);
	
	/**
	 * get point that stay in the center of the screen
	 *
	 * @param pageSize
	 * 		size of page that want to show in the center
	 * @return point of center screen (you can set in <code>setLocation()</code> directly)
	 */
	public static Point getCenterLocation(Dimension pageSize) {
		return new Point((int) ((DimensionUtilities.getScreenSize().getWidth() / 2) - (pageSize.getWidth() / 2)), (int) ((DimensionUtilities.getScreenSize().getHeight() / 2) - (pageSize.getHeight() / 2)));
	}
	
	/**
	 * get point to show page at center of the old page <br>
	 * if don't have old page (oldPage is <code>null</code>) this method will return center of the screen
	 *
	 * @param oldPage
	 * 		current page
	 * @param newPage
	 * 		new page that want to show
	 * @return point (you can set in <code>setLocation()</code> directly)
	 */
	public static Point getCenterPage(Window oldPage, Window newPage) {
		if (oldPage == null) {
			return getCenterLocation(newPage.getSize());
		}
		Point currPoint = oldPage.getLocation();
		
		int newX = (currPoint.x + (oldPage.getWidth() / 2)) - (newPage.getWidth() / 2);
		int newY = (currPoint.y + (oldPage.getHeight() / 2)) - (newPage.getHeight() / 2);
		
		return new Point(newX, newY);
	}
}
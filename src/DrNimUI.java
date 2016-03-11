//******************************************************************************
//
// File:    DrNimUI.java
// Package: ---
// Unit:    Class DrNimUI
//
// This Java source file is copyright (C) 2016 by Alan Kaminsky. All rights
// reserved. For further information, contact the author, Alan Kaminsky, at
// ark@cs.rit.edu.
//
// This Java source file is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by the Free
// Software Foundation; either version 3 of the License, or (at your option) any
// later version.
//
// This Java source file is distributed in the hope that it will be useful, but
// WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
// FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
// details.
//
// You may obtain a copy of the GNU General Public License on the World Wide Web
// at http://www.gnu.org/licenses/gpl.html.
//
//******************************************************************************

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Class DrNimUI provides the user interface for the Dr. Nim network game.
 *
 * @author  Alan Kaminsky
 * @version 08-Mar-2016
 */
public class DrNimUI
	{

	/**
	 * Class for a Swing widget displaying a bunch of marbles.
	 */
	private static class MarblePanel
		extends JPanel
		{
		private static final int N = 15;
		private static final int W = 35;
		private static final int H = 35;
		private static ImageIcon icon =
			new ImageIcon (MarblePanel.class.getResource ("marble.png"));

		private int count = 0;

		// Construct a new marble panel.
		public MarblePanel()
			{
			Dimension dim = new Dimension (N*W, H);
			setMinimumSize (dim);
			setMaximumSize (dim);
			setPreferredSize (dim);
			}

		// Set the number of marbles in this marble panel.
		public void setCount
			(int count) // Number of marbles
			{
			count = Math.max (0, Math.min (count, N));
			if (this.count != count)
				{
				this.count = count;
				repaint();
				}
			}

		// Paint this heap panel.
		protected void paintComponent
			(Graphics g) // Graphics context
			{
			super.paintComponent (g);

			// Clone graphics context.
			Graphics2D g2d = (Graphics2D) g.create();

			// Turn on antialiasing.
			g2d.setRenderingHint
				(RenderingHints.KEY_ANTIALIASING,
				 RenderingHints.VALUE_ANTIALIAS_ON);

			// Paint marbles.
			for (int i = 0; i < count; ++ i)
				{
				icon.paintIcon (this, g2d, (N - 1 - i)*W, 0);
				}
			}
		}

// Hidden data members.

	private static final int GAP = 10;

	private JFrame frame;
	private MarblePanel marblePanel;
	private JTextField msgField;
	private JButton oneButton;
	private JButton twoButton;
	private JButton threeButton;
	private JButton passButton;

// Hidden constructors.

	/**
	 * Construct a new Dr. Nim UI.
	 */
	private DrNimUI()
		{
		frame = new JFrame ("Dr. Nim");
		JPanel panel = new JPanel();
		panel.setLayout (new BoxLayout (panel, BoxLayout.Y_AXIS));
		frame.add (panel);
		panel.setBorder (BorderFactory.createEmptyBorder (GAP, GAP, GAP, GAP));

		marblePanel = new MarblePanel();
		panel.add (marblePanel);
		panel.add (Box.createVerticalStrut (GAP));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout (new BoxLayout (buttonPanel, BoxLayout.X_AXIS));
		panel.add (buttonPanel);

		msgField = new JTextField (10);
		msgField.setEditable (false);
		buttonPanel.add (msgField);
		buttonPanel.add (Box.createHorizontalStrut (GAP));
		oneButton = new JButton ("Take 1");
		buttonPanel.add (oneButton);
		buttonPanel.add (Box.createHorizontalStrut (GAP));
		twoButton = new JButton ("Take 2");
		buttonPanel.add (twoButton);
		buttonPanel.add (Box.createHorizontalStrut (GAP));
		threeButton = new JButton ("Take 3");
		buttonPanel.add (threeButton);
		buttonPanel.add (Box.createHorizontalStrut (GAP));
		passButton = new JButton ("Pass");
		buttonPanel.add (passButton);

		frame.pack();
		frame.setVisible (true);
		}

	}

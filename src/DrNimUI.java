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
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Class DrNimUI provides the user interface for the Dr. Nim network game.
 *
 * @author  Alan Kaminsky
 * @version 08-Mar-2016
 *
 * @reviser Jiaqi Gu
 * @version 28-Mar-2016
 */
public class DrNimUI implements ModelListener
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

	//private int remaining;
	private ViewListener viewListener;


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

        // On user input actions, trigger the view listener.
			oneButton.addActionListener (new ActionListener()
			{
				public void actionPerformed (ActionEvent e)
				{
					msgField.setText("");
					oneButton.setEnabled(false);
					twoButton.setEnabled(false);
					threeButton.setEnabled(false);
					passButton.setEnabled(false);

					viewListener.takeOne();
				}
			});

			twoButton.addActionListener (new ActionListener()
			{
				public void actionPerformed (ActionEvent e)
				{
					msgField.setText("");
					oneButton.setEnabled(false);
					twoButton.setEnabled(false);
					threeButton.setEnabled(false);
					passButton.setEnabled(false);

					viewListener.takeTwo();
				}
			});

			threeButton.addActionListener (new ActionListener()
			{
				public void actionPerformed (ActionEvent e)
				{
					msgField.setText("");
					oneButton.setEnabled(false);
					twoButton.setEnabled(false);
					threeButton.setEnabled(false);
					passButton.setEnabled(false);
					try
					{
						viewListener.takeThree();
					}
					catch (IllegalArgumentException exc)
					{
						//shouldn't happen
					}


				}
			});

			passButton.addActionListener (new ActionListener()
			{
				public void actionPerformed (ActionEvent e)
				{
					msgField.setText("");
					oneButton.setEnabled(false);
					twoButton.setEnabled(false);
					threeButton.setEnabled(false);
					passButton.setEnabled(false);

					viewListener.takePass();
				}
			});

		}

// Exported operations.

		/**
		 * An object holding a reference to a Password Crack UI.
		 */
		private static class DrNimUIRef
		{
			public DrNimUI ui;
		}

		/**
		 * Construct a new Password Crack UI.
		 */
		public static DrNimUI create()
		{
			final DrNimUIRef ref = new DrNimUIRef();
			onSwingThreadDo (new Runnable()
			{
				public void run()
				{
					ref.ui = new DrNimUI();
					ref.ui.marblePanel.setCount(15); //Initially, it should start at 15 marbles
					ref.ui.msgField.setText("Your turn!"); //Set text message
				}
			});
			return ref.ui;
		}


		/**
		 * Execute the given runnable object on the Swing thread.
		 */
		private static void onSwingThreadDo
		(Runnable task)
		{
			try
			{
				SwingUtilities.invokeAndWait (task);
			}
			catch (Throwable exc)
			{
				exc.printStackTrace (System.err);
				System.exit (1);
			}
		}


		// Exported operations

		/**
		 * Set the view listener object for this Password Crack UI.
		 *
		 * @param  viewListener  View listener.
		 */
		public void setViewListener
		(final ViewListener viewListener)
		{
			onSwingThreadDo (new Runnable()
			{
				public void run()
				{
					DrNimUI.this.viewListener = viewListener;
				}
			});
		}


		/**
		 * Perform the process that displays a certain number of marbles
		 *
		 * @param num, number of marble remaining
		 */
		public void display(final int num){
			//marblePanel.setCount(num);
			onSwingThreadDo (new Runnable()
			{
				public void run()
				{
					DrNimUI.this.marblePanel.setCount(num);
				}
			});
		}

		/**
		 * Perform the process that the player wins
		 */
		public void playerWin(){
			//delay for 2 seconds
			//try{Thread.sleep(2000);}catch (InterruptedException ie){}//Exception shouldn't happen
			onSwingThreadDo (new Runnable()
			{
				public void run()
				{
					msgField.setText("You won!"); //Set text message

					//reset the game
					marblePanel.setCount(15);
					oneButton.setEnabled(true);
					twoButton.setEnabled(true);
					threeButton.setEnabled(true);
					passButton.setEnabled(true);
				}
			});
		}

		/**
		 * Perform the process that the Dr.Dim wins
		 */
		public void dimWin(){
			onSwingThreadDo (new Runnable()
			{
				public void run()
				{
					msgField.setText("Dr. Nim won!"); //Set text message

					//reset the game
					marblePanel.setCount(15);
					oneButton.setEnabled(true);
					twoButton.setEnabled(true);
					threeButton.setEnabled(true);
					passButton.setEnabled(true);
				}
			});
		}

		/**
		 * Player's turn, display "Your turn!"
		 */
		public void playerTurn(){
			onSwingThreadDo (new Runnable()
			{
				public void run()
				{
					msgField.setText("Your turn!"); //Set text message
					oneButton.setEnabled(true);
					twoButton.setEnabled(true);
					threeButton.setEnabled(true);
					passButton.setEnabled(true);
				}
			});
		}

		/**
		 * Dr. Dim's turn, display "Dr. Nim's turn!"
		 */
		public void dimTurn(){
			onSwingThreadDo (new Runnable()
			{
				public void run()
				{
					msgField.setText("Dr. Nim's turn!"); //Set text message
				}
			});
		}

	}

//******************************************************************************
//
// File:    ModelListener.java
// Package: ---
// Unit:    Interface ModelListener
//
// This Java source file is for CSCI-251 Project 3
//
//******************************************************************************

import java.io.IOException;

/**
 * Interface ModelListener specifies the interface for an object that is
 * triggered by events from the model object in the DrNim Application.
 *
 * (The idea and structure of the code is from Prof. Kaminsky)
 *
 * @author  Jiaqi Gu
 * @version 28-Mar-2016
 */
public interface ModelListener {

    /**
     * Perform the process that displays a certain number of marbles
     *
     * @param num, the number of marble displays
     */
    public void display(int num) throws IOException;

    /**
     * Perform the process that the player wins
     */
    public void playerWin() throws IOException;

    /**
     * Perform the process that the Dr.Dim wins
     */
    public void dimWin() throws IOException;

    /**
     * Player's turn, display "Your turn!"
     */
    public void playerTurn() throws IOException;

    /**
     * Dr. Dim's turn, display "Dr. Nim's turn!"
     */
    public void dimTurn() throws IOException;

    /**
     * Reset the game
     */
    public void reset() throws IOException;

    /**
     * Make buttons unable
     */
    public void unableButton() throws IOException;


}

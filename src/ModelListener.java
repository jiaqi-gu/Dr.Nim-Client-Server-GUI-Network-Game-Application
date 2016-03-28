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
 * @author  Jiaqi Gu
 * @version 28-Mar-2016
 */
public interface ModelListener {

    /**
     * Perform the process that displays a certain number of marbles
     *
     * @param num, the number of marble displays
     */
    public void display(int num);

    /**
     * Perform the process that the player wins
     */
    public void playerWin();

    /**
     * Perform the process that the Dr.Dim wins
     */
    public void dimWin();

    /**
     * Player's turn, display "Your turn!"
     */
    public void playerTurn();

    /**
     * Dr. Dim's turn, display "Dr. Nim's turn!"
     */
    public void dimTurn();


}

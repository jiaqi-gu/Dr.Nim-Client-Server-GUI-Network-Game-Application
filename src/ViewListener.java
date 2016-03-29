//******************************************************************************
//
// File:    ViewListener.java
// Package: ---
// Unit:    Interface ViewListener
//
// This Java source file is for CSCI-251 Project 3
//
//******************************************************************************

import java.io.IOException;

/**
 * Interface ViewListener specifies the interface for an object that is
 * triggered by events from the view object in the DrNim Application.
 *
 *  (The idea and structure of the code is from Prof. Kaminsky)
 *
 * @author  Jiaqi Gu
 * @version 28-Mar-2016
 */
public interface ViewListener {

    /**
     * Take one marble
     */
    public void takeOne() throws IOException;

    /**
     * Take two marbles
     */
    public void takeTwo() throws IOException;

    /**
     * Take three marbles
     */
    public void takeThree() throws IOException;

    /**
     * Pass and take no marble
     */
    public void takePass() throws IOException;

}

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
 * @author  Jiaqi Gu
 * @version 28-Mar-2016
 */
public interface ViewListener {

    /**
     * Take one marble
     */
    public void takeOne();

    /**
     * Take two marbles
     */
    public void takeTwo();

    /**
     * Take three marbles
     */
    public void takeThree();

    /**
     * Pass and take no marble
     */
    public void takePass();

}

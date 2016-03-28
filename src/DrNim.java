//******************************************************************************
//
// File:    DrNim.java
// Package: ---
// Unit:    Class DrNim
//
// This Java source file is for CSCI-251 Project 3
//
//******************************************************************************

import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Class PasswordCrackClient is the client main program
 *
 * Usage: java DrNim <I>host</I> <I>port</I>
 *
 * @author  Jiaqi Gu
 * @version 28-Mar-2016
 */
public class DrNim {

    /**
     * Main program.
     */
    public static void main
    (String[] args)
            throws Exception
    {
        DrNimModel model = new DrNimModel();
        DrNimUI ui = DrNimUI.create();
        model.setModelListener(ui);
        ui.setViewListener(model);
    }


}

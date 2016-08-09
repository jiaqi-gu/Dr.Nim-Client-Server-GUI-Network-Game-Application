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
 *  (The idea and structure of the code is from Prof. Kaminsky)
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
        if (args.length != 2) usage();
        String host = args[0];
        int port = Integer.parseInt (args[1]);

        Socket socket = new Socket();
        socket.connect (new InetSocketAddress (host, port));

        DrNimUI view = DrNimUI.create();
        ModelProxy proxy = new ModelProxy (socket);
        view.setViewListener (proxy);
        proxy.setModelListener (view);
    }

    /**
     * Print a usage message and exit.
     */
    private static void usage()
    {
        System.err.println ("Usage: java DrNim <host> <port>");
        System.exit (1);
    }


}

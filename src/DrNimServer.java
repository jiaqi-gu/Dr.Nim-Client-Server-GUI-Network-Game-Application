//******************************************************************************
//
// File:    DrNimServer.java
// Package: ---
// Unit:    Class DrNimServer
//
// This Java source file is for CSCI-251 Project 3
//
//******************************************************************************

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class DrNimServer is the server main program
 *
 * Usage: java DrNimServer <I>host</I> <I>port</I>
 *
 *  (The idea and structure of the code is from Prof. Kaminsky)
 *
 * @author  Jiaqi Gu
 * @version 28-Mar-2016
 */
public class DrNimServer {
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

        ServerSocket serversocket = new ServerSocket();
        serversocket.bind (new InetSocketAddress (host, port));

        for (;;)
        {
            Socket socket = serversocket.accept();
            ViewProxy proxy = new ViewProxy (socket);
            DrNimModel model = new DrNimModel();
            model.setModelListener (proxy);
            proxy.setViewListener (model);
        }
    }

    /**
     * Print a usage message and exit.
     */
    private static void usage()
    {
        System.err.println ("Usage: java DrNimServer <host> <port>");
        System.exit (1);
    }
}

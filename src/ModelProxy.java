//******************************************************************************
//
// File:    ModelProxy.java
// Package: ---
// Unit:    Class ModelProxy
//
// This Java source file is for CSCI-251 Project 3
//
//******************************************************************************

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Class ModelProxy provides the network proxy for the model object in the
 * DrNim Application. The model proxy resides in the client program and
 * communicates with the server program.
 *
 * (The idea and structure of the code is from Prof. Kaminsky)
 *
 * @author  Jiaqi Gu
 * @version 28-Mar-2016
 */
public class ModelProxy implements ViewListener {

    // Hidden data members.

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private ModelListener modelListener;

// Exported constructors.

    /**
     * Construct a new model proxy.
     *
     * @param  socket  Socket.
     *
     * @exception  IOException
     *     Thrown if an I/O error occurred.
     */
    public ModelProxy
    (Socket socket)
            throws IOException
    {
        this.socket = socket;
        out = new DataOutputStream (socket.getOutputStream());
        in = new DataInputStream (socket.getInputStream());
    }


    // Exported operations.

    /**
     * Set the model listener object for this model proxy.
     *
     * @param  modelListener  Model listener.
     */
    public void setModelListener
    (ModelListener modelListener)
    {
        this.modelListener = modelListener;
        new ReaderThread() .start();
    }

    /**
     * Take one marble
     */
    public void takeOne() throws IOException{
        out.writeByte ('A');
        out.flush();
    }

    /**
     * Take two marbles
     */
    public void takeTwo() throws IOException{
        out.writeByte ('B');
        out.flush();
    }

    /**
     * Take three marbles
     */
    public void takeThree() throws IOException{
        out.writeByte ('C');
        out.flush();
    }

    /**
     * Pass and take no marble
     */
    public void takePass() throws IOException{
        out.writeByte ('D');
        out.flush();
    }

    //hidden helper class (from Prof. Kaminsky)

    /**
     * Class ReaderThread receives messages from the network, decodes them, and
     * invokes the proper methods to process them.
     *
     * @author  Alan Kaminsky
     * @version 21-Dec-2009
     *
     * @reviser Jiaqi Gu
     * @version 28-Mar-2016
     */
    private class ReaderThread
            extends Thread
    {
        int num;

        public void run()
        {
            try
            {
                for (;;)
                {
                    byte b = in.readByte();
                    switch (b)
                    {
                        case 'M':
                            //display
                            num =  in.readByte();
                            modelListener.display(num);
                            break;
                        case 'W':
                            //Win
                            modelListener.playerWin();
                            break;
                        case 'L':
                            //Lose the game, and Dr. Dim wins
                            modelListener.dimWin();
                            break;
                        case 'P':
                            //Player's turn
                            modelListener.playerTurn();
                            break;
                        case 'D':
                            //Dim's turn
                            modelListener.dimTurn();
                            break;
                        case 'R':
                            //Dim's turn
                            modelListener.reset();
                            break;
                        case 'U':
                            //Dim's turn
                            modelListener.unableButton();
                            break;
                        default:
                            System.err.println ("Bad message");
                            break;
                    }
                }
            }
            catch (IOException exc)
            {
            }
            finally
            {
                try
                {
                    socket.close();
                }
                catch (IOException exc)
                {
                }
            }
        }
    }

}

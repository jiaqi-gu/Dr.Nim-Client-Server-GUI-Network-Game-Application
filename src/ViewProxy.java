//******************************************************************************
//
// File:    ViewProxy.java
// Package: ---
// Unit:    Class ViewProxy
//
// This Java source file is for CSCI-251 Project 3
//
//******************************************************************************

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Class ViewProxy provides the network proxy for the view object in the
 * DrNim Application. The view proxy resides in the server program and
 * communicates with the client program.
 *
 * (The idea and structure of the code is from Prof. Kaminsky)
 *
 * @author  Jiaqi Gu
 * @version 28-Mar-2016
 */
public class ViewProxy
        implements ModelListener
{
    // Hidden data members.

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private ViewListener viewListener;

    // Exported constructors.

    /**
     * Construct a new view proxy.
     *
     * @param  socket  Socket.
     */
    public ViewProxy
    (Socket socket)
            throws IOException
    {
        this.socket = socket;
        out = new DataOutputStream (socket.getOutputStream());
        in = new DataInputStream (socket.getInputStream());
    }

    /**
     * Set the view listener object for this view proxy.
     *
     * @param  viewListener  View listener.
     */
    public void setViewListener
    (ViewListener viewListener)
    {
        this.viewListener = viewListener;
        new ReaderThread() .start();
    }

    // Exported constructors.

    /**
     * Perform the process that displays a certain number of marbles
     *
     * @param num, the number of marble displays
     */
    public void display(int num) throws IOException{
        out.writeByte ('M');
        out.writeByte(num);
        out.flush();
    }

    /**
     * Perform the process that the player wins
     */
    public void playerWin() throws IOException{
        out.writeByte ('W');
        out.flush();
    }

    /**
     * Perform the process that the Dr.Dim wins
     */
    public void dimWin() throws IOException{
        out.writeByte ('L');
        out.flush();
    }

    /**
     * Player's turn, display "Your turn!"
     */
    public void playerTurn() throws IOException{
        out.writeByte ('P');
        out.flush();
    }

    /**
     * Dr. Dim's turn, display "Dr. Nim's turn!"
     */
    public void dimTurn() throws IOException{
        out.writeByte ('D');
        out.flush();
    }

    /**
     * Make buttons unable
     */
    public void unableButton() throws IOException{
        out.writeByte ('U');
        out.flush();
    }

    /**
     * Enable buttons
     */
    public void enableButtons() throws IOException{
        out.writeByte ('E');
        out.flush();
    }

    /**
     * Display "" (empty text)
     */
    public void emptyText() throws IOException{
        out.writeByte ('T');
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

        public void run()
        {
            try
            {
                for (;;)
                {
                    byte b = in.readByte();
                    switch (b)
                    {
                        case 'A':
                            //OneButton
                            viewListener.takeOne();
                            break;
                        case 'B':
                            //TwoButton
                            viewListener.takeTwo();
                            break;
                        case 'C':
                            //ThreeButton
                            viewListener.takeThree();
                            break;
                        case 'D':
                            //Pass
                            viewListener.takePass();
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
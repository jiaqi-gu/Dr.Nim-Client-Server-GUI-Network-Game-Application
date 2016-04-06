//******************************************************************************
//
// File:    DrNimModel.java
// Package: ---
// Unit:    Class DrNimModel
//
// This Java source file is for CSCI-251 Project 3
//
//******************************************************************************

import java.io.IOException;

/**
 * Class DrNimModel provides the application logic for the DrNim application
 *
 * Usage: java DrNim <I>host</I> <I>port</I>
 *
 *  (The idea and structure of the code is from Prof. Kaminsky)
 *
 * @author  Jiaqi Gu
 * @version 28-Mar-2016
 */
public class DrNimModel implements ViewListener{

    // Hidden data members.

    private ModelListener modelListener;
    private int Remaining;


    // Exported constructors.

    public DrNimModel(){
        this.Remaining = 15;
    }


    // Exported operations.

    /**
     * Set the model listener object for this Password Crack model.
     *
     * @param  modelListener  Model listener.
     */
    public synchronized void setModelListener
    (ModelListener modelListener)
    {
        this.modelListener = modelListener;
        (new initialization()).start();
    }


    /**
     * Take one marble
     */
    public void takeOne(){
        (new perform(1)).start();
    }

    /**
     * Take two marbles
     */
    public void takeTwo(){
        (new perform(2)).start();
    }

    /**
     * Take three marbles
     */
    public void takeThree(){
        (new perform(3)).start();
    }

    /**
     * Pass and take no marble
     */
    public void takePass(){
        (new perform(0)).start();
    }


    /**
     * A thread class to initialize the game
     */
    public class initialization extends Thread{

        public void run(){
            try{
                modelListener.reset();
                modelListener.playerTurn();
            }catch (IOException E){
                //Shouldn't happen
            }
        }
    }

    /**
     * A thread class to perform the game process
     */
    public class perform extends Thread{

        private int number; //the number of marbles to be taken out

        public perform(int num){
            this.number = num;
        }

        public void run(){
            //make buttons unable first
            try{
                modelListener.unableButton();
            }catch (IOException E){
                //Shouldn't happen
            }

            //perform player's behavior
            for(int i = 0;i<number;i++){
                try{Thread.sleep(500);}catch (InterruptedException ie){System.err.println("Delay failed");}
                Remaining-=1;
                try{
                    modelListener.display(Remaining);
                }catch (IOException E){
                    //Shouldn't happen
                }
                //If remaining equals to 0, the player wins
                if(Remaining==0){
                    try{
                        modelListener.playerWin();
                        try{Thread.sleep(2000);}catch (InterruptedException ie){System.err.println("Delay failed");}
                        Remaining =15;
                        modelListener.reset();
                        modelListener.playerTurn();
                    }catch (IOException E){
                        //Shouldn't happen
                    }
                    return;//all marbles have been taken out
                }
            }

            //if there is at least one marble taken off
            if(number != 0){
                //delay for 0.5 sec after the last marble disappears
                try{Thread.sleep(500);}catch (InterruptedException ie){System.err.println("Delay failed");}
            }



            //Dr. Dim's Turn (If there is at least one marble remained)
            try{
                modelListener.dimTurn();//display message
            }catch (IOException E){
                //Shouldn't happen
            }


            //delay for 2 seconds while Dr.Dim "thinks"
            try{Thread.sleep(2000);}catch (InterruptedException ie){}//Exception shouldn't happen


            //If there are three or fewer marbles left, Dr. Nim takes all the marbles.
            if(Remaining<=3){
                while(Remaining>0){
                    try{Thread.sleep(500);}catch (InterruptedException ie){System.err.println("Delay failed");}
                    Remaining-=1;
                    try{
                        modelListener.display(Remaining);
                    }catch (IOException E){
                        //Shouldn't happen
                    }

                    //If remaining equals to 0, the Dr. dim wins
                    if(Remaining==0){
                        try{
                            modelListener.dimWin();
                            try{Thread.sleep(2000);}catch (InterruptedException ie){System.err.println("Delay failed");}
                            Remaining =15;
                            modelListener.reset();
                            modelListener.playerTurn();
                        }catch (IOException E){
                            //Shouldn't happen
                        }
                        return;//all marbles have been taken out
                    }
                }
                return;//Game finished
            }

            // If the number of marbles greater than 3, and the number left is a multiple of 3, Dr. Nim takes two marbles.
            if(Remaining % 3==0){
                for(int i = 0;i<2;i++){
                    try{Thread.sleep(500);}catch (InterruptedException ie){System.err.println("Delay failed");}
                    Remaining-=1;
                    try{
                        modelListener.display(Remaining);
                    }catch (IOException E){
                        //Shouldn't happen
                    }
                }
                try{Thread.sleep(500);}catch (InterruptedException ie){System.err.println("Delay failed");}
            }else{
                //Otherwise, Dr. Nim takes one marble.
                try{Thread.sleep(500);}catch (InterruptedException ie){System.err.println("Delay failed");}
                Remaining-=1;
                try{
                    modelListener.display(Remaining);
                }catch (IOException E){
                    //Shouldn't happen
                }
                try{Thread.sleep(500);}catch (InterruptedException ie){System.err.println("Delay failed");}
            }


            //Make the player to do the next move (If there is at least one marble remained)
            try{
                modelListener.playerTurn();
            }catch (IOException E){
                //Shouldn't happen
            }


        }
    }


}

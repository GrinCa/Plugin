/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hissshaw;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julien
 */
public class Chronos extends Thread{
    
    public Chronos(GameManager gameManager){
        this.chronos = 0;
        sampleTime = 100;
        this.gameManager = gameManager;
    }
    
    public void setIsRunning(boolean isRunning){
        this.isRunning = isRunning;
    }
    
    
    public int getChronos(){
        return chronos;
    }
    
    public void process(){
        chronos += sampleTime;
        if(!check())
            setIsRunning(false);
    }
    
    public boolean check(){
        if(gameManager.getPlayerList().size() < gameManager.getNbrMinJoueurs())
            return false;
        return true;
    }
    
    @Override
    public void run(){
        System.out.println("[Thread Jeu] : ########## Thread lancé ##########");
        while(isRunning){
            try {
                sleep(sampleTime);
                process();
            } catch (InterruptedException ex) {
                Logger.getLogger(Chronos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("[Thread Jeu] : ########## Terminated ##########");
        return;
    }
    
    private int sampleTime;
    private boolean isRunning;
    private int chronos;
    private GameManager gameManager;
    
}

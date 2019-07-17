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
    
    public Chronos(PluginJeu pluginJeu, GameManager gameManager){
        this.pluginJeu = pluginJeu;
        this.chronos = 0;
        this.sampleTime = 100;
        this.gameManager = gameManager;
        this.game = new Game(pluginJeu);
        this.attente = 3_000;
    }
    
    public void setIsRunning(boolean isRunning){
        this.isRunning = isRunning;
    }
    
    public Game getGame(){
        return this.game;
    }
    
    
    public int getChronos(){
        return this.chronos;
    }
    
    private void process(){
        chronos += sampleTime;
        if(!check())
            setIsRunning(false);
    }
    
    private boolean check(){
        if(chronos >= attente){
            if(!game.isAlive()){
                game = new Game(pluginJeu);
                game.setIsRunning(true);
                game.start();
            }
            return false;
        }  
        return true;
    }
    
    @Override
    public void run(){
        System.out.println("[Thread Chronos] : ########## Chronos launched ##########");
        while(isRunning){
            try {
                sleep(sampleTime);
                process();
            } catch (InterruptedException ex) {
                Logger.getLogger(Chronos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("[Thread Chronos] : ########## Chronos terminated ##########");
        return;
    }
    
    private int sampleTime;
    private boolean isRunning;
    private int chronos;
    private int attente;
    private GameManager gameManager;
    private Game game;
    private PluginJeu pluginJeu;
    
}

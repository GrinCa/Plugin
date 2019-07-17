/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hissshaw;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockExplodeEvent;

/**
 *
 * @author Julien
 */
public class Game extends Thread {

    public Game(PluginJeu pluginJeu) {
        this.pluginJeu = pluginJeu;
        this.sampleTime = 100;
        this.chronos = 0;
        this.blockLocation = new Location(Bukkit.getWorld("world"), 0d, 0d, 0d);
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    private void process() {
        chronos += sampleTime;
    }

    private void launchGame() {
        gameListeners = new GameListeners(pluginJeu, this);
        Bukkit.getServer().getPluginManager().registerEvents(gameListeners, pluginJeu);
        createBlockLocation();
    }

    private void createBlockLocation() {
        double x = 0;
        double y = 0;
        double playerNumber = 0;
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            x += p.getLocation().getX();
            y += p.getLocation().getY();
            playerNumber++;
        }
        x = x / playerNumber;
        y = y / playerNumber;
        blockLocation.setX(x);
        blockLocation.setY(y);
    }

    public Location getBlockLocation() {
        return this.blockLocation;
    }

    @Override
    public void run() {
        System.out.println("[Thread Game] : ########## Game launched ##########");
        launchGame();
        while (isRunning) {
            process();
            try {
                sleep(sampleTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("[Thread Game] : ########## Game terminated ##########");
        return;
    }

    private boolean isRunning;
    private int sampleTime;
    private GameListeners gameListeners;
    private PluginJeu pluginJeu;
    private Location blockLocation;
    private long chronos;

}

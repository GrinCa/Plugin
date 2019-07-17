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
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

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
        this.playDuration = 100 * 1000;
        this.radius = 5;
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
        double z = 0;
        double playerNumber = 0;
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            x += p.getLocation().getX();
            z += p.getLocation().getZ();
            playerNumber++;
        }
        x = Math.floor(x / playerNumber);
        z = Math.floor(z / playerNumber);
        blockLocation.setX(x + Math.floor(Math.random() * radius));
        blockLocation.setY(80 + Math.floor(3 * Math.random()));
        blockLocation.setZ(z + Math.floor(Math.random() * radius));
        
        setMaterial(Bukkit.getServer().getPlayer("Hissshaw"));
        setBlock();
    }

    private void setBlock() {
        new BukkitRunnable() {
            public void run() {
                blockLocation.getBlock().setType(material);
            }
        }.runTask(pluginJeu);
    }

    private boolean check() {
        if (chronos > playDuration) {
            return false;
        }
        return true;
    }

    public Location getBlockLocation() {
        return this.blockLocation;
    }

    private void inform() {
        Bukkit.broadcastMessage("echec");
    }
    
    public void setMaterial(Player player){
        this.material = player.getInventory().getContents()[0].getType();
        System.out.println(this.material.toString());
    }

    @Override
    public void run() {
        System.out.println("[Thread Game] : ########## Game launched ##########");
        launchGame();
        while (isRunning) {
            process();
            if (!check()) {
                inform();
                pluginJeu.getGameManager().stop();
            }

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
    private long playDuration;
    private int radius;
    private Material material = Material.GRASS;

}

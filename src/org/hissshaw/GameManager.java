/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hissshaw;

import java.util.ArrayList;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 *
 * @author Julien
 */
public class GameManager implements Listener {

    public GameManager() {
        this.nbrMinJoueurs = 1;
        playerList = new ArrayList<Player>();
        chronos = new Chronos(this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent ev) {
        addPlayer(ev.getPlayer());
        launch();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent ev) {
        removePlayer(ev.getPlayer());
    }

    public void launch() {
        if (playerList.size() >= nbrMinJoueurs) {
            if (!chronos.isAlive()) {
                chronos = new Chronos(this);
                chronos.setIsRunning(true);
                chronos.start();
            }

        }
    }
    
    public void stop(){
        chronos.setIsRunning(false);
    }

    public void addPlayer(Player player) {
        boolean belong = false;
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).getName().equalsIgnoreCase(player.getName())) {
                belong = true;
            }
        }
        if (!belong) {
            playerList.add(player);
        }
    }

    public void removePlayer(Player player) {
        playerList.remove(player);
    }
    
    public ArrayList<Player> getPlayerList(){
        return this.playerList;
    }
    
    public int getNbrMinJoueurs(){
        return this.nbrMinJoueurs;
    }

    private int nbrMinJoueurs;
    private ArrayList<Player> playerList;
    private Chronos chronos;

}

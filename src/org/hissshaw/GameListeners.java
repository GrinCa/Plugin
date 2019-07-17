/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hissshaw;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 *
 * @author Julien
 */
public class GameListeners implements Listener{
    
    public GameListeners(PluginJeu pluginJeu, Game game){
        this.pluginJeu = pluginJeu;
        this.game = game;
    }
    
    /* lISTENERS */
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent ev){
        /*ev.getPlayer().sendMessage("X : "+ev.getBlock().getLocation().getX()+"   Z : "+ev.getBlock().getLocation().getZ());
        ev.getPlayer().sendMessage("choix du jeu : "+game.getBlockLocation().getX()+"   "+game.getBlockLocation().getZ());*/
        if(ev.getBlock().getLocation().equals(game.getBlockLocation()) &&
                pluginJeu.getGameManager().getChronos().getGame().isAlive()){
            Bukkit.broadcastMessage("Jeu terminé");
            pluginJeu.getGameManager().stop();
        }
    }
    
    private Game game;
    private PluginJeu pluginJeu;
}

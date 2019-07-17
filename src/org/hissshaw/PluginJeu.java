/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hissshaw;

import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Julien
 */
public class PluginJeu extends JavaPlugin {

    @Override
    public void onEnable() {
        registerListener();
        registerCommands();
    }

    @Override
    public void onDisable() {
        gameManager.stop();
    }

    private void registerListener() {
        gameManager = new GameManager(this);
        gameManager.scanPlayerList();
        getServer().getPluginManager().registerEvents(gameManager, this);
    }

    private void registerCommands() {
        commandsManager = new CommandsManager(gameManager);
        getCommand("online").setExecutor(commandsManager);
        getCommand("abort").setExecutor(commandsManager);
        getCommand("launch").setExecutor(commandsManager);
        getCommand("scan").setExecutor(commandsManager);
    }
    
    public GameManager getGameManager(){
        return this.gameManager;
    }

    private GameManager gameManager;
    private CommandsManager commandsManager;
}

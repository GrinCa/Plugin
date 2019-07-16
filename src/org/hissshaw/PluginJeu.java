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
        gameManager = new GameManager();
        getServer().getPluginManager().registerEvents(gameManager, this);
    }

    private void registerCommands() {
        commandsManager = new CommandsManager(gameManager);
        getCommand("online").setExecutor(commandsManager);
    }

    private GameManager gameManager;
    private CommandsManager commandsManager;
}

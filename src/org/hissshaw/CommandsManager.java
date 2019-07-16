/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hissshaw;

import java.util.ArrayList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Julien
 */
public class CommandsManager implements CommandExecutor{
    
    public CommandsManager(GameManager gameManager){
        this.playerList = gameManager.getPlayerList();
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String msg, String[] args) {
        if (command.getName().equalsIgnoreCase("online")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage("Joueurs en ligne :\n");
                for (Player p : playerList) {
                    player.sendMessage("-" + p.getName() + "\n");
                }
            }

        }
        return false;
    }
    
    private ArrayList<Player> playerList;
    
}

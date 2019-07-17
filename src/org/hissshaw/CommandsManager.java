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
        this.gameManager = gameManager;
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

        } else if(command.getName().equalsIgnoreCase("abort")){
            if(gameManager.getChronos().isAlive())
                gameManager.stop();
            else{
                if(sender instanceof Player){
                    Player p = (Player) sender;
                    p.sendMessage("[Infos] : Le thread n'est pas en cours d'execution");
                }else
                    System.out.println("[PluginJeu : Infos] : Le thread n'est pas en cours d'execution");
            }    
        }else if(command.getName().equalsIgnoreCase("launch")){
            if(!gameManager.getChronos().isAlive()){
                boolean isPossible = gameManager.launch();
                if(isPossible){
                    if(sender instanceof Player){
                        Player p = (Player) sender;
                        p.sendMessage("[Infos] : Le thread s'éxecute");
                    }else{
                        System.out.println("[PluginJeu : Infos] : Le thread s'éxecute");
                    }
                } else {
                    if(sender instanceof Player){
                        Player p = (Player) sender;
                        p.sendMessage("[Infos] : Le thread ne peut être lancé");
                    }else{
                        System.out.println("[PluginJeu : Infos] : Le thread ne peut être lancé");
                    }
                }
            }
        }
        
        return false;
    }
    
    private GameManager gameManager;
    private ArrayList<Player> playerList;
    
}

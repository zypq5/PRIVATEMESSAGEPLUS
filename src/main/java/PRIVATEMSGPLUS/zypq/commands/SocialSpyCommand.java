package PRIVATEMSGPLUS.zypq.commands;

import PRIVATEMSGPLUS.zypq.PMPLUS;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;


public class SocialSpyCommand implements CommandExecutor {

    public boolean onCommand( CommandSender sender,  Command command,  String label,  String[] args) {
        if(sender instanceof Player player)
            if(player.hasPermission(PMPLUS.instance.getConfig().getString("spy-perm"))) {
                if (player.getPersistentDataContainer().has(PMPLUS.hasSocialSpy)) {
                    player.sendMessage(PMPLUS.format(PMPLUS.instance.getConfig().getString("spy-toggle-off")));
                    player.getPersistentDataContainer().remove(PMPLUS.hasSocialSpy);
                } else {
                     player.sendMessage(PMPLUS.format(PMPLUS.instance.getConfig().getString("spy-toggle-on")));
                    player.getPersistentDataContainer().set(PMPLUS.hasSocialSpy, PersistentDataType.STRING, "hasSocialSpy");
                }
            }else{
                player.sendMessage(PMPLUS.format(PMPLUS.instance.getConfig().getString("spy-noperm-msg")));
            }


        return true;
    }
}

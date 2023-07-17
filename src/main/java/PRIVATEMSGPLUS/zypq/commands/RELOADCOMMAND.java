package PRIVATEMSGPLUS.zypq.commands;

import PRIVATEMSGPLUS.zypq.PMPLUS;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class RELOADCOMMAND implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label, String[] args) {
        if (args.length != 0 && args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission(PMPLUS.instance.getConfig().getString("reload-perm"))) {
                PMPLUS.instance.reloadConfig();
                sender.sendMessage(PMPLUS.format(PMPLUS.instance.getConfig().getString("reload-msg")));
            } else {
                sender.sendMessage(PMPLUS.format(PMPLUS.instance.getConfig().getString("reload-noperm-msg")));
            }
        }
        return true;
    }
}

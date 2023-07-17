package PRIVATEMSGPLUS.zypq.commands;

import PRIVATEMSGPLUS.zypq.PMPLUS;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class REPLYCOMMAND implements CommandExecutor {

    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) {
                player.sendMessage(PMPLUS.format("<red>Invalid argument"));
            }else{
                Player target = Bukkit.getPlayer(MESSAGECOMMAND.PM_PLUS.get(player.getUniqueId()));
                if (target == null) {
                    player.sendMessage(PMPLUS.format(PMPLUS.instance.getConfig().getString("reply-offline")));
                    return true;
                } else if (!MESSAGECOMMAND.PM_PLUS.containsKey(player.getUniqueId())) {
                    player.sendMessage(PMPLUS.format(PMPLUS.instance.getConfig().getString("not-messaged")));
                    return true;
                }else {
                    String msgrecieved = PMPLUS.instance.getConfig().getString("recieved-msg").replaceAll("%player%", player.getName()).replaceAll("%target%", target.getName()).replaceAll("%message%", String.join(" ", args));
                    target.sendMessage(PMPLUS.format(msgrecieved));

                    String msgsent = PMPLUS.instance.getConfig().getString("sent-msg").replaceAll("%player%", player.getName()).replaceAll("%target%", target.getName()).replaceAll("%message%", String.join(" ", args));
                    player.sendMessage(PMPLUS.format(msgsent));
                    MESSAGECOMMAND.PM_PLUS.put(target.getUniqueId(), player.getUniqueId());
                    MESSAGECOMMAND.PM_PLUS.put(player.getUniqueId(), target.getUniqueId());

                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.getPersistentDataContainer().has(PMPLUS.hasSocialSpy)) {
                            p.sendMessage(PMPLUS.format(PMPLUS.instance.getConfig().getString("spy-msg").replaceAll("%player%", player.getName()).replaceAll("%target%", target.getName()).replaceAll("%message%", String.join(" ", args))));
                        }
                    }
                }
            }
        }

        return true;
    }
}

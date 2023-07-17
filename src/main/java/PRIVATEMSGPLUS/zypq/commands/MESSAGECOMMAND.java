package PRIVATEMSGPLUS.zypq.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import PRIVATEMSGPLUS.zypq.PMPLUS;
import org.jetbrains.annotations.Nullable;

import java.util.*;


public class MESSAGECOMMAND implements CommandExecutor {
    public static final Map<UUID, UUID> PM_PLUS = new HashMap();

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) {
                player.sendMessage(PMPLUS.format("<red>[PM+] <gray>Invalid argument"));
            } else if (args.length >= 2) {
                Player target = Bukkit.getPlayer(args[0]);

                if (target == null || !target.isOnline()) {
                    player.sendMessage(PMPLUS.format("<red>[PM+] <gray>That player is not online!"));
                } else if (target.equals(player)) {
                    player.sendMessage(PMPLUS.format("<red>[PM+] <gray>You can't message yourself!"));
                } else {
                    String msgrecieved = PMPLUS.instance.getConfig().getString("recieved-msg").replaceAll("%player%", player.getName()).replaceAll("%target%", target.getName()).replaceAll("%message%", String.join(" ", args).substring(args[0].length()).trim());
                    target.sendMessage(PMPLUS.format(msgrecieved));

                    String msgsent = PMPLUS.instance.getConfig().getString("sent-msg").replaceAll("%player%", player.getName()).replaceAll("%target%", target.getName()).replaceAll("%message%", String.join(" ", args).substring(args[0].length()).trim());
                    player.sendMessage(PMPLUS.format(msgsent));
                    PM_PLUS.put(target.getUniqueId(), player.getUniqueId());
                    PM_PLUS.put(player.getUniqueId(), target.getUniqueId());

                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.getPersistentDataContainer().has(PMPLUS.hasSocialSpy)) {
                            p.sendMessage(PMPLUS.format(PMPLUS.instance.getConfig().getString("spy-msg").replaceAll("%player%", player.getName()).replaceAll("%target%", target.getName()).replaceAll("%message%", String.join(" ", args).substring(args[0].length()).trim())));
                        }
                    }
                }
            }
        }
        return true;
    }
    }



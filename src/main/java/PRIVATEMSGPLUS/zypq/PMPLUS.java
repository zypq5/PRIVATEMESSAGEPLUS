package PRIVATEMSGPLUS.zypq;

import PRIVATEMSGPLUS.zypq.commands.MESSAGECOMMAND;
import PRIVATEMSGPLUS.zypq.commands.RELOADCOMMAND;
import PRIVATEMSGPLUS.zypq.files.updatechecker;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import PRIVATEMSGPLUS.zypq.commands.REPLYCOMMAND;
import PRIVATEMSGPLUS.zypq.commands.SocialSpyCommand;

public final class PMPLUS extends JavaPlugin {
    public static PMPLUS instance;
    public static NamespacedKey hasSocialSpy;
    public static @NotNull Component format(String string) {
        return MiniMessage.miniMessage().deserialize(string);
    }
    @Override
    public void onEnable() {
        Bukkit.getServer().getLogger().info("-----------------------");
        Bukkit.getServer().getLogger().info("      PRIVATEMESSAGE+");
        Bukkit.getServer().getLogger().info("  ---THANKS FOR USING---");
        Bukkit.getServer().getLogger().info("       VERSION 1.0");
        Bukkit.getServer().getLogger().info("-----------------------");
        instance = this;
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        hasSocialSpy = new NamespacedKey(this, "hasSocialSpy");
        getCommand("message").setExecutor(new MESSAGECOMMAND());
        getCommand("socialspy").setExecutor(new SocialSpyCommand());
        getCommand("reply").setExecutor(new REPLYCOMMAND());
        getCommand("pmplus").setExecutor(new RELOADCOMMAND());

        new updatechecker(111300).getVersion(version -> {
            if (this.getDescription().getVersion().equals(version)) {
                getLogger().info("[PM+] NEW UPDATE AVAILABE " + getDescription());
            } else {
                getLogger().info("[PM+] NO NEW UPDATES AVAILABLE");
            }
        });

    }
}

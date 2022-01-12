package me.brand0n_.prisonplaceholderaddon;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class PrisonPlaceholderAddon extends JavaPlugin {

    @Override
    public void onEnable() {
        // Save config
        saveDefaultConfig();

        if (Bukkit.getPluginManager().getPlugin("Prison") == null) {
            getLogger().severe(ChatColor.translateAlternateColorCodes('&', "&cCan't find Prison, this plugin will only work for the 'Prison' plugin please install it now. The link is: https://www.spigotmc.org/resources/prison.1223/"));
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        // Small check to make sure that PlaceholderAPI is installed
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceholderFix(this).register();
            getLogger().info(ChatColor.translateAlternateColorCodes('&', "&aFound placeholderAPI!"));
        } else {
            getLogger().severe(ChatColor.translateAlternateColorCodes('&', "&cCan't find placeholderAPI, please make sure to install it for the plugin to work."));
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }
}

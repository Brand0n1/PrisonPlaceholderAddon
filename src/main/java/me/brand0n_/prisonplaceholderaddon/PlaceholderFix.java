package me.brand0n_.prisonplaceholderaddon;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;

import java.util.Objects;

public class PlaceholderFix extends PlaceholderExpansion {
    private static PrisonPlaceholderAddon plugin = PrisonPlaceholderAddon.getPlugin(PrisonPlaceholderAddon.class); // Get this from main

    public PlaceholderFix(PrisonPlaceholderAddon plugin) {
        PlaceholderFix.plugin = plugin;
    }

    @Override
    public String getIdentifier() {
        return "prisonAddon";
    }

    @Override
    public String getAuthor() {
        return "Brand0n_";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if (params.equalsIgnoreCase("name")) {
            return player == null ? null : player.getName(); // "name" requires the player to be valid
        }

        if (params.equalsIgnoreCase("prison_rcf_default")) {
            return getRankupMessage(player, "%prison_rcf_default%");
        }
        if (params.equalsIgnoreCase("prison_rcf_default::nFormat:#,##0.00:0:kmbt")) {
            return getRankupMessage(player, "%prison_rcf_default::nFormat:#,##0.00:0:kmbt%");
        }
        if (params.equalsIgnoreCase("prison_rcb_default")) {
            return getRankupBar(player, "%prison_rcb_default%");
        }
        if (params.equalsIgnoreCase("prison_rcb_prestiges")) {
            return getPrestigeBar(player, "%prison_rcb_prestiges%");
        }

        return null; // Placeholder is unknown by the Expansion
    }

    private String getRankupMessage(OfflinePlayer p, String str) {
        String maxRank = plugin.getConfig().getString("max rank");
        String rank = PlaceholderAPI.setPlaceholders(p, "%prison_rank_default%");
        String price = PlaceholderAPI.setPlaceholders(p, str);
        String prestigePrice = PlaceholderAPI.setPlaceholders(p, "%prison_rcf_prestiges::nFormat:#,##0.00:0:kmbt%");
        if (price.isEmpty() || price.equalsIgnoreCase(" ")) {
            if (rank.equalsIgnoreCase(maxRank)) {
                return prestigePrice;
            }
        }
        return price;
    }

    private String getRankupBar(OfflinePlayer p, String str) {
        String maxRank = plugin.getConfig().getString("max rank");
        String rankupMessage = plugin.getConfig().getString("rankup message");
        String rank = PlaceholderAPI.setPlaceholders(p, "%prison_rank_default%");
        String bar = PlaceholderAPI.setPlaceholders(p, str);
        String strippedBar = ChatColor.stripColor(bar);
        String barFormat = plugin.getConfig().getString("bar format");
        if (barFormat == null)
            barFormat = bar;
        else
            barFormat = barFormat.replace("%bar%", bar);
        bar = ChatColor.translateAlternateColorCodes('&', barFormat);
        if (bar.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&a" + strippedBar))) {
            return rankupMessage;
        }
        if (bar.isEmpty() || bar.equalsIgnoreCase(" ")) {
            if (rank.equalsIgnoreCase(maxRank)) {
                return getPrestigeBar(p, "%prison_rcb_prestiges%");
            }
        }
        return bar;
    }

    private String getPrestigeBar(OfflinePlayer p, String str) {
        String bar = PlaceholderAPI.setPlaceholders(p, str);
        String prestigeMessage = plugin.getConfig().getString("prestige message");
        String strippedBar = ChatColor.stripColor(bar);
        String barFormat = plugin.getConfig().getString("bar format");
        if (barFormat == null)
            barFormat = bar;
        else
            barFormat = barFormat.replace("%bar%", bar);
        bar = ChatColor.translateAlternateColorCodes('&', barFormat);
        if (bar.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&a" + strippedBar))) {
            return prestigeMessage;
        }
        return bar;
    }
}

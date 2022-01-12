package me.brand0n_.prisonplaceholderaddon;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;

public class PlaceholderFix extends PlaceholderExpansion {

    private final PrisonPlaceholderAddon plugin;

    public PlaceholderFix(PrisonPlaceholderAddon plugin) {
        this.plugin = plugin;
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
        String rank = PlaceholderAPI.setPlaceholders(p, "%prison_rank_default%");
        String price = PlaceholderAPI.setPlaceholders(p, str);
        String prestigePrice = PlaceholderAPI.setPlaceholders(p, "%prison_rcf_prestiges::nFormat:#,##0.00:0:kmbt%");
        if (price.isEmpty() || price.equalsIgnoreCase(" ")) {
            if (rank.equalsIgnoreCase("Z")) {
                return prestigePrice;
            }
        }
        return price;
    }

    private String getRankupBar(OfflinePlayer p, String str) {
        String rank = PlaceholderAPI.setPlaceholders(p, "%prison_rank_default%");
        String bar = PlaceholderAPI.setPlaceholders(p, str);
        String strippedBar = ChatColor.stripColor(bar);
        if (bar.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&a" + strippedBar))) {
            return "/rankup";
        }
        if (bar.isEmpty() || bar.equalsIgnoreCase(" ")) {
            if (rank.equalsIgnoreCase("Z")) {
                return getPrestigeBar(p, "%prison_rcb_prestiges%");
            }
        }
//        bar = ChatColor.translateAlternateColorCodes('&', "&7["+bar+"&7]&r");
        return bar;
    }

    private String getPrestigeBar(OfflinePlayer p, String str) {
        String bar = PlaceholderAPI.setPlaceholders(p, str);
        String strippedBar = ChatColor.stripColor(bar);
        if (bar.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&a" + strippedBar))) {
            return "/prestige";
        }
//        bar = ChatColor.translateAlternateColorCodes('&', "&7["+bar+"&7]&r");
        return bar;
    }
}

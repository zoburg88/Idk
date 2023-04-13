package zoburg88.github.io.zoburgclans;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class SaveLoadManager {


    private final ZoburgClans plugin;

    public SaveLoadManager(ZoburgClans plugin) {
        this.plugin = plugin;
    }

    public void saveClans(List<Clan> clans) {
        FileConfiguration config = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "clans.yml"));
        config.set("clans", clans);
        try {
            config.save(new File(plugin.getDataFolder(), "clans.yml"));
        } catch (IOException e) {
            plugin.getLogger().warning("Failed to save clans.yml file.");
            e.printStackTrace();
        }
    }

    public static void savePlayerData(ZoburgClans plugin, ClanPlayer player) {
        File playerFile = new File(plugin.getDataFolder(), "players/" + player.getId() + ".yml");
        FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);

        // Set values in the config
        playerConfig.set("id", player.getId());
        playerConfig.set("name", player.getName());
        playerConfig.set("clan", player.getClan() != null ? player.getClan().getId() : null);
        playerConfig.set("rank", player.getRole());

        // Save the config to file
        try {
            playerConfig.save(playerFile);
        } catch (IOException e) {
            plugin.getLogger().warning("Failed to save player data for " + player.getName());
        }
    }

    public static ClanPlayer loadPlayerData(Player player, ZoburgClans plugin) {
        File playerFile = new File(plugin.getDataFolder(), "players/" + player.getUniqueId() + ".yml");
        if (!playerFile.exists()) {
            return null;
        }
        FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);

        // Get values from the config
        UUID id = UUID.fromString(playerConfig.getString("id"));
        String name = playerConfig.getString("name");
        UUID clanId = UUID.fromString(playerConfig.getString("clan"));
        Clan clan = null;
        if (clanId != null) {
            clan = ClanManager.getInstance().getClan(clanId);
        }
        String rank = playerConfig.getString("rank");

        // Create and return the ClanPlayer object
        ClanPlayer clanPlayer = new ClanPlayer(player.getUniqueId(), name);
        clanPlayer.setId(id);
        clanPlayer.setClan(clan);
        clanPlayer.setRank(ClanRole.valueOf(rank));
        return clanPlayer;
    }


    public List<Clan> loadClans() {
        FileConfiguration config = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "clans.yml"));
        if (config.contains("clans")) {
            List<Clan> loadedClans = config.getList("clans").stream().map(obj -> (Clan) obj).collect(Collectors.toList());
            plugin.getLogger().info("Loaded " + loadedClans.size() + " clans.");
            return loadedClans;
        } else {
            plugin.getLogger().info("No clans found to load.");
            return new ArrayList<>();
        }
    }
}

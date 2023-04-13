package zoburg88.github.io.zoburgclans;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ClanManager {

    private static ClanManager instance;

    private final Map<UUID, ClanPlayer> players = new HashMap<>();
    private final List<Clan> clans = new ArrayList<>();

    private final SaveLoadManager saveLoadManager;
    private final ZoburgClans plugin;

    private ClanManager(ZoburgClans plugin) {
        this.plugin = plugin;
        saveLoadManager = new SaveLoadManager(plugin);
        clans.addAll(saveLoadManager.loadClans());
    }


    public static void initialize(ZoburgClans plugin) {
        if (instance == null) {
            instance = new ClanManager(plugin);
        }
    }
    public static ZoburgClans getPlugin() {
        return JavaPlugin.getPlugin(ZoburgClans.class);
    }

    public static ClanManager getInstance() {
        return instance;
    }

    public Clan createClan(Player leader, String name) {
        Clan clan = new Clan(name, leader);
        clans.add(clan);
        return clan;
    }

    public void removeClan(Clan clan) {
        clans.remove(clan);
        for (ClanPlayer player : players.values()) {
            if (player.getClan() == clan) {
                player.setClan(null);
            }
        }
    }

    public Clan getClan(UUID playerId) {
        ClanPlayer player = players.get(playerId);
        return player != null ? player.getClan() : null;
    }

    public void addPlayer(ClanPlayer player) {
        players.put(player.getId(), player);
    }

    public void removePlayer(ClanPlayer player) {
        players.remove(player.getId());
    }

    public ClanPlayer getPlayer(UUID playerId) {
        return players.get(playerId);
    }

    public List<Clan> getClans() {
        return clans;
    }

    public void saveClans() {
        saveLoadManager.saveClans(clans);
    }

    public void loadClans() {
        clans.clear();
        clans.addAll(saveLoadManager.loadClans());
    }

    public void savePlayerData(ClanPlayer player) {
        SaveLoadManager.savePlayerData(plugin, player);
    }

    public ClanPlayer loadPlayerData(Player player) {
        return SaveLoadManager.loadPlayerData(player, plugin);
    }

}

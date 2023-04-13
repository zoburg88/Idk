package zoburg88.github.io.zoburgclans;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

public class ClanPlayerManager {

    private static ClanPlayerManager instance;

    private final Map<UUID, ClanPlayer> players;
    private Map<UUID, ClanPlayer> clanPlayers = new HashMap<>();

    private ClanPlayerManager() {
        this.players = new HashMap<>();
    }

    public static ClanPlayerManager getInstance() {
        if (instance == null) {
            instance = new ClanPlayerManager();
        }
        return instance;
    }

    public void addPlayer(ClanPlayer player) {
        players.put(player.getId(), player);
    }

    public ClanPlayer getPlayer(UUID id) {
        return players.get(id);
    }

    public void removePlayer(UUID id) {
        players.remove(id);
    }
    public ClanPlayer getClanPlayer(Player player) {
        UUID playerId = player.getUniqueId();
        return clanPlayers.get(playerId);
    }


}

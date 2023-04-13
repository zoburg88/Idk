package zoburg88.github.io.zoburgclans.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import zoburg88.github.io.zoburgclans.Clan;
import zoburg88.github.io.zoburgclans.ClanManager;
import zoburg88.github.io.zoburgclans.SaveLoadManager;
import zoburg88.github.io.zoburgclans.ClanPlayer;
import zoburg88.github.io.zoburgclans.ZoburgClans;

public class ClanJoinLeaveListener implements Listener {

    private final ZoburgClans plugin;

    public ClanJoinLeaveListener(ZoburgClans plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Clan clan = ClanManager.getInstance().getClan(player.getUniqueId());
        ClanPlayer clanPlayer = SaveLoadManager.loadPlayerData(player, plugin); // Load player data
        if (clan != null) {
            clan.broadcastMessage(player.getName() + " has joined the game.");
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Clan clan = ClanManager.getInstance().getClan(player.getUniqueId());
        if (clan != null) {
            clan.broadcastMessage(player.getName() + " has left the game.");
        }
    }
}

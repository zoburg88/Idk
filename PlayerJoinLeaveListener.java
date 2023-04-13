package zoburg88.github.io.zoburgclans.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import zoburg88.github.io.zoburgclans.ClanPlayer;
import zoburg88.github.io.zoburgclans.ClanManager;
import zoburg88.github.io.zoburgclans.SaveLoadManager;

public class PlayerJoinLeaveListener implements Listener {

    private final SaveLoadManager saveLoadManager;

    public PlayerJoinLeaveListener(SaveLoadManager saveLoadManager) {
        this.saveLoadManager = saveLoadManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        ClanPlayer player = saveLoadManager.loadPlayerData(event.getPlayer(), ClanManager.getInstance().getPlugin());
        if (player != null) {
            ClanManager.getInstance().addPlayer(player);
        } else {
            player = new ClanPlayer(event.getPlayer().getUniqueId(), event.getPlayer().getName());
            ClanManager.getInstance().addPlayer(player);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        ClanPlayer player = ClanManager.getInstance().getPlayer(event.getPlayer().getUniqueId());
        saveLoadManager.savePlayerData(ClanManager.getInstance().getPlugin(), player);
        ClanManager.getInstance().removePlayer(player);
    }
}

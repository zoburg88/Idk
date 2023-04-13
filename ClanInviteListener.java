package zoburg88.github.io.zoburgclans.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import zoburg88.github.io.zoburgclans.Clan;
import zoburg88.github.io.zoburgclans.ClanInviteManager;
import zoburg88.github.io.zoburgclans.ClanManager;

public class ClanInviteListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Clan clan = ClanManager.getInstance().getClan(player.getUniqueId());
        if (clan == null) {
            return;
        }
        ClanInviteManager.getInstance().removePendingInvite(player.getUniqueId(), clan);
    }

}

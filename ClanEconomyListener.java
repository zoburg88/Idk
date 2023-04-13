package zoburg88.github.io.zoburgclans;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ClanEconomyListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Clan clan = ClanManager.getInstance().getClan(player.getUniqueId());
        if (clan != null) {
            double balance = ClanEconomy.getInstance().getBalance(clan);
            player.sendMessage(String.format("Your clan (%s) has a balance of %.2f", clan.getName(), balance));
        }
    }
}

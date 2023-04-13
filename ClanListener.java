package zoburg88.github.io.zoburgclans;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ClanListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        Clan clan = ClanManager.getInstance().getClan(player.getUniqueId());
        if (clan != null) {
            String message = event.getMessage();
            String formattedMessage = ChatColor.GRAY + "[" + ChatColor.RED + clan.getName() + ChatColor.GRAY + "]" + ChatColor.RESET + " " + message;
            event.setFormat(formattedMessage);
        }
    }

}

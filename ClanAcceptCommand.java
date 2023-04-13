package zoburg88.github.io.zoburgclans;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;
import zoburg88.github.io.zoburgclans.ClanInviteManager;
import zoburg88.github.io.zoburgclans.Clan;
import zoburg88.github.io.zoburgclans.ClanRole;
public class ClanAcceptCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be executed by players.");
            return true;
        }

        Player player = (Player) sender;

        Clan clan = ClanInviteManager.getInstance().getInvitedClan(player.getUniqueId());

        if (clan == null) {
            player.sendMessage(ChatColor.RED + "You have no pending clan invites.");
            return true;
        }

        clan.addMember(player, ClanRole.MEMBER);
        ClanInviteManager.getInstance().removePendingInvite(player.getUniqueId(), clan);
        player.sendMessage(ChatColor.GREEN + "You have joined the clan " + clan.getName() + ".");
        clan.broadcastMessage(ChatColor.YELLOW + player.getName() + " has joined the clan.");
        return true;
    }
}

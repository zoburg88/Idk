package zoburg88.github.io.zoburgclans;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ClanInviteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be executed by players.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage(ChatColor.RED + "Usage: /clan invite <player>");
            return true;
        }

        String playerName = args[0];
        Player targetPlayer = player.getServer().getPlayer(playerName);

        if (targetPlayer == null || !targetPlayer.isOnline()) {
            player.sendMessage(ChatColor.RED + "That player is not online.");
            return true;
        }

        Clan clan = ClanManager.getInstance().getClan(player.getUniqueId());

        if (clan == null) {
            player.sendMessage(ChatColor.RED + "You are not in a clan.");
            return true;
        }

        if (clan.getRole(player) != ClanRole.LEADER) {
            player.sendMessage(ChatColor.RED + "You must be the leader of your clan to use this command.");
            return true;
        }

        if (clan.hasMember(targetPlayer)) {
            player.sendMessage(ChatColor.RED + "That player is already a member of your clan.");
            return true;
        }

        if (ClanInviteManager.getInstance().hasPendingInvite(targetPlayer.getUniqueId(), clan)) {
            player.sendMessage(ChatColor.RED + "That player has already been invited to your clan.");
            return true;
        }

        ClanInviteManager.getInstance().addPendingInvite(targetPlayer.getUniqueId(), clan);
        player.sendMessage(ChatColor.GREEN + "You have invited " + targetPlayer.getName() + " to join your clan.");
        targetPlayer.sendMessage(ChatColor.YELLOW + "You have been invited to join " + clan.getName() + ".");
        targetPlayer.sendMessage(ChatColor.YELLOW + "Type /clan accept to join.");
        return true;
    }
}

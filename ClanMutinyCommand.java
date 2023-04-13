package zoburg88.github.io.zoburgclans;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClanMutinyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be executed by players.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage(ChatColor.RED + "Usage: /clan mutiny <player>");
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

        if (clan.getRole(targetPlayer) == ClanRole.LEADER) {
            player.sendMessage(ChatColor.RED + "You cannot mutiny against the leader of your clan.");
            return true;
        }

        clan.removeMember(targetPlayer);
        clan.addMember(targetPlayer, ClanRole.LEADER);
        clan.broadcastMessage(ChatColor.GREEN + targetPlayer.getName() + " has been promoted to clan leader by " + player.getName() + "!");
        return true;
    }
}

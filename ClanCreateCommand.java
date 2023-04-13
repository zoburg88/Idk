package zoburg88.github.io.zoburgclans;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ClanCreateCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be executed by players.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage(ChatColor.RED + "Usage: /clan create <name>");
            return true;
        }

        String clanName = args[0];

        if (ClanManager.getInstance().getClan(player.getUniqueId()) != null) {
            player.sendMessage(ChatColor.RED + "You are already in a clan.");
            return true;
        }

        if (ClanManager.getInstance().getClan(UUID.fromString(clanName)) != null) {
            player.sendMessage(ChatColor.RED + "A clan with that name already exists.");
            return true;
        }

        Clan clan = new Clan(clanName, player);
        clan.addMember(player, ClanRole.LEADER);
        player.sendMessage(ChatColor.GREEN + "You have created the clan " + clanName + ".");
        return true;
    }
}

package zoburg88.github.io.zoburgclans.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import zoburg88.github.io.zoburgclans.Clan;
import zoburg88.github.io.zoburgclans.ClanManager;
import zoburg88.github.io.zoburgclans.ClanPlayer;
import zoburg88.github.io.zoburgclans.ClanPlayerManager;

public class ClanLeaveCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be run by a player.");
            return true;
        }

        Player player = (Player) sender;
        ClanPlayer clanPlayer = ClanPlayerManager.getInstance().getClanPlayer(player);
        if (clanPlayer == null) {
            player.sendMessage(ChatColor.RED + "You are not in a clan.");
            return true;
        }

        Clan clan = clanPlayer.getClan();
        clan.removeMember(player);

        player.sendMessage(ChatColor.GREEN + "You have left the clan.");
        return true;
    }
}

package zoburg88.github.io.zoburgclans.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import zoburg88.github.io.zoburgclans.Clan;
import zoburg88.github.io.zoburgclans.ClanManager;

import java.util.Collection;

public class ClanListCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Collection<Clan> clans = ClanManager.getInstance().getClans();
        if (clans.isEmpty()) {
            sender.sendMessage("There are no clans to list.");
            return true;
        }
        sender.sendMessage("Clans:");
        for (Clan clan : clans) {
            sender.sendMessage("- " + clan.getName());
        }
        return true;
    }

}

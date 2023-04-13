package zoburg88.github.io.zoburgclans;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClanEconomyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be executed by players.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Usage: /clan deposit|withdraw amount");
            return true;
        }

        if (args.length == 1) {
            player.sendMessage(ChatColor.RED + "Please specify an amount to deposit or withdraw.");
            return true;
        }

        double amount;
        try {
            amount = Double.parseDouble(args[1]);
        } catch (NumberFormatException e) {
            player.sendMessage(ChatColor.RED + "Invalid amount specified.");
            return true;
        }

        if (args[0].equalsIgnoreCase("deposit")) {
            ClanEconomy.getInstance().deposit(player, amount);
        } else if (args[0].equalsIgnoreCase("withdraw")) {
            ClanEconomy.getInstance().withdraw(player, amount);
        } else {
            player.sendMessage(ChatColor.RED + "Usage: /clan deposit|withdraw amount");
            return true;
        }

        return true;
    }

}

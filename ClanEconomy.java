package zoburg88.github.io.zoburgclans;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
public class ClanEconomy {

    private static ClanEconomy instance;

    private final Economy economy;
    private final Map<UUID, Double> clanVaults;

    private ClanEconomy() {
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            throw new IllegalStateException("No economy plugin found");
        }
        this.economy = rsp.getProvider();
        this.clanVaults = new HashMap<>();
    }

    public static ClanEconomy getInstance() {
        if (instance == null) {
            instance = new ClanEconomy();
        }
        return instance;
    }

    public void deposit(Player player, double amount) {
        Clan clan = ClanManager.getInstance().getClan(player.getUniqueId());

        if (clan == null) {
            player.sendMessage(ChatColor.RED + "You are not in a clan.");
            return;
        }

        if (amount < 0) {
            player.sendMessage(ChatColor.RED + "You cannot deposit a negative amount of money.");
            return;
        }

        if (!economy.has(player, amount)) {
            player.sendMessage(ChatColor.RED + "You do not have enough money to deposit that amount.");
            return;
        }

        economy.withdrawPlayer(player, amount);
        clanVaults.putIfAbsent(clan.getClanId(), 0.0);
        double clanVaultBalance = clanVaults.get(clan.getClanId());
        clanVaults.put(clan.getClanId(), clanVaultBalance + amount);
        player.sendMessage(ChatColor.GREEN + "You have deposited " + amount + " into your clan's vault.");
    }


    public void removeClan(Clan clan) {
        clanVaults.remove(clan.getName());
    }
    public Economy getEconomy() {
        return economy;
    }
    public void withdraw(Player player, double amount) {
        Clan clan = ClanManager.getInstance().getClan(player.getUniqueId());

        if (clan == null) {
            player.sendMessage(ChatColor.RED + "You are not in a clan.");
            return;
        }

        if (amount < 0) {
            player.sendMessage(ChatColor.RED + "You cannot withdraw a negative amount of money.");
            return;
        }

        if (clan.getRole(player) != ClanRole.LEADER) {
            player.sendMessage(ChatColor.RED + "You must be the leader of your clan to use this command.");
            return;
        }

        double clanVaultBalance = clanVaults.getOrDefault(clan.getClanId(), 0.0);

        if (amount > clanVaultBalance) {
            player.sendMessage(ChatColor.RED + "Your clan does not have enough money to withdraw that amount.");
            return;
        }

        economy.depositPlayer(player, amount);
        clanVaults.put(clan.getClanId(), clanVaultBalance - amount);
        player.sendMessage(ChatColor.GREEN + "You have withdrawn " + amount + " from your clan's vault.");
    }

    public double getBalance(Clan clan) {
        return clanVaults.getOrDefault(clan.getClanId(), 0.0);
    }




}

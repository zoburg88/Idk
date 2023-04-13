package zoburg88.github.io.zoburgclans;

import org.bukkit.plugin.Plugin;

public class ClanSaveTask implements Runnable {

    private final Plugin plugin;

    public ClanSaveTask(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        ClanManager.getInstance().saveClans();
        plugin.getLogger().info("Clan data saved.");
    }

    public void start() {
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, this, 12000L, 12000L);
    }

    public void stop() {
        plugin.getServer().getScheduler().cancelTasks(plugin);
    }

}

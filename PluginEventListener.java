package zoburg88.github.io.zoburgclans;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;

public class PluginEventListener implements Listener {

    private final ClanManager clanManager;

    public PluginEventListener(ClanManager clanManager) {
        this.clanManager = clanManager;
    }

    @EventHandler
    public void onPluginEnable(PluginEnableEvent event) {
        // Check if the plugin that was enabled is our own plugin
        if (event.getPlugin().getName().equals("ZoburgClans")) {
            // Initialize the ClanManager instance
            ClanManager.initialize((ZoburgClans) event.getPlugin());
        }
    }

    @EventHandler
    public void onPluginDisable(PluginDisableEvent event) {
        // Check if the plugin that was disabled is our own plugin
        if (event.getPlugin().getName().equals("ZoburgClans")) {
            // Save the clans to file before the plugin is disabled
            clanManager.saveClans();
        }
    }
}

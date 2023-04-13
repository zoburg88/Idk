package zoburg88.github.io.zoburgclans;

import org.bukkit.plugin.java.JavaPlugin;
import zoburg88.github.io.zoburgclans.commands.ClanLeaveCommand;
import zoburg88.github.io.zoburgclans.commands.ClanListCommand;
import zoburg88.github.io.zoburgclans.listeners.ClanJoinLeaveListener;



public class ZoburgClans extends JavaPlugin {

    private static ZoburgClans instance;

    @Override
    public void onEnable() {
        instance = this;

        // Register commands
        getCommand("claninvite").setExecutor(new ClanInviteCommand());
        getCommand("clanaccept").setExecutor(new ClanAcceptCommand());
        getCommand("clancreate").setExecutor(new ClanCreateCommand());
        getCommand("clanleave").setExecutor(new ClanLeaveCommand());
        getCommand("clanlist").setExecutor(new ClanListCommand());
        getCommand("clanmutiny").setExecutor(new ClanMutinyCommand());


        // Register listeners
        getServer().getPluginManager().registerEvents(new ClanEconomyListener(), this);
        getServer().getPluginManager().registerEvents(new zoburg88.github.io.zoburgclans.listeners.ClanInviteListener(), this);
        getServer().getPluginManager().registerEvents(new ClanJoinLeaveListener(this), this);
        getServer().getPluginManager().registerEvents(new zoburg88.github.io.zoburgclans.listeners.PlayerJoinLeaveListener(new SaveLoadManager(this)), this);

        ClanSaveTask clanSaveTask = new ClanSaveTask(this);
        clanSaveTask.start();

        ClanManager.getInstance().loadClans();

    }

    @Override
    public void onDisable() {
        // Save data, disconnect from databases, etc.
    }

    public static ZoburgClans getInstance() {
        return instance;
    }

}

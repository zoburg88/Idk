package zoburg88.github.io.zoburgclans;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Clan {

    private UUID id;
    private String name;
    private UUID leader;
    private Map<UUID, ClanRole> members;


    public Clan(String name, Player leader) {

        this.id = UUID.randomUUID();
        this.name = name;
        this.leader = leader.getUniqueId();
        this.members = new HashMap<>();
        addMember(leader, ClanRole.LEADER);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addMember(Player player, ClanRole role) {
        ClanPlayer clanPlayer = new ClanPlayer(player.getUniqueId(), player.getName());
        clanPlayer.setClan(this);
        clanPlayer.setRank(role);
        members.put(player.getUniqueId(), role);
        ClanManager.getInstance().addPlayer(clanPlayer);
    }

    public void removeMember(Player player) {
        ClanPlayer clanPlayer = ClanManager.getInstance().getPlayer(player.getUniqueId());
        if (clanPlayer != null) {
            members.remove(player.getUniqueId());
            ClanManager.getInstance().removePlayer(clanPlayer);
        }
    }

    public boolean hasMember(Player player) {
        return members.containsKey(player.getUniqueId());
    }

    public ClanRole getRole(Player player) {
        return members.get(player.getUniqueId());
    }

    public Map<UUID, ClanRole> getMembers() {
        return members;
    }

    public int getOnlineMemberCount() {
        int count = 0;
        for (UUID playerId : members.keySet()) {
            Player player = Bukkit.getPlayer(playerId);
            if (player != null && player.isOnline()) {
                count++;
            }
        }
        return count;
    }

    public void broadcastMessage(String message) {
        for (UUID playerId : members.keySet()) {
            Player player = Bukkit.getPlayer(playerId);
            if (player != null && player.isOnline()) {
                player.sendMessage(message);
            }
        }
    }

    public UUID getClanId() {
        return id;
    }
}

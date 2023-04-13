package zoburg88.github.io.zoburgclans;

import java.util.UUID;
import org.bukkit.Bukkit;

public class ClanPlayer {
    private final UUID playerId;

    private Clan clan;
    private UUID id;
    private String name;
    private ClanRole role;

    public ClanPlayer(UUID playerId, String name) {
        this.playerId = playerId;
        this.id = UUID.randomUUID();
        this.name = name;
        this.role = ClanRole.MEMBER;
    }

    public ClanPlayer(UUID playerId, UUID id, String name, ClanRole role) {
        this.playerId = playerId;
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public UUID getPlayerId() {
        return playerId;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }
    public void setRank(ClanRole rank) {
        this.role = rank;
    }


    public boolean isInClan() {
        return clan != null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClanRole getRole() {
        return role;
    }

    public void setRole(ClanRole role) {
        this.role = role;
    }
}

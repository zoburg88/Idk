package zoburg88.github.io.zoburgclans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Collection;
public class ClanInviteManager {

    private static ClanInviteManager instance;

    private final Map<UUID, List<ClanInvite>> pendingInvites;

    private ClanInviteManager() {
        this.pendingInvites = new HashMap<>();
    }

    public static ClanInviteManager getInstance() {
        if (instance == null) {
            instance = new ClanInviteManager();
        }
        return instance;
    }

    public void addPendingInvite(UUID playerId, Clan clan) {
        ClanInvite invite = new ClanInvite(playerId, clan);
        List<ClanInvite> invites = pendingInvites.getOrDefault(playerId, new ArrayList<>());
        invites.add(invite);
        pendingInvites.put(playerId, invites);
    }

    public boolean hasPendingInvite(UUID playerId, Clan clan) {
        List<ClanInvite> invites = pendingInvites.get(playerId);
        if (invites == null) {
            return false;
        }
        for (ClanInvite invite : invites) {
            if (invite.getClan().equals(clan)) {
                return true;
            }
        }
        return false;
    }

    public Clan getInvitedClan(UUID playerId) {
        List<ClanInvite> invites = pendingInvites.get(playerId);
        if (invites == null || invites.isEmpty()) {
            return null;
        }
        return invites.get(0).getClan();
    }

    public void removePendingInvite(UUID playerId, Clan clan) {
        List<ClanInvite> invites = pendingInvites.get(playerId);
        if (invites == null) {
            return;
        }
        invites.removeIf(invite -> invite.getClan().equals(clan));
        if (invites.isEmpty()) {
            pendingInvites.remove(playerId);
        }
    }

    private static class ClanInvite {

        private final UUID playerId;
        private final Clan clan;

        public ClanInvite(UUID playerId, Clan clan) {
            this.playerId = playerId;
            this.clan = clan;
        }

        public UUID getPlayerId() {
            return playerId;
        }

        public Clan getClan() {
            return clan;
        }

    }

}

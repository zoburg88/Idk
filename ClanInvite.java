package zoburg88.github.io.zoburgclans;

import java.util.UUID;

public class ClanInvite {
    private final UUID inviterId;
    private final UUID invitedId;
    private final Clan clan;

    public ClanInvite(UUID inviterId, UUID invitedId, Clan clan) {
        this.inviterId = inviterId;
        this.invitedId = invitedId;
        this.clan = clan;
    }

    public UUID getInviterId() {
        return inviterId;
    }

    public UUID getInvitedId() {
        return invitedId;
    }

    public Clan getClan() {
        return clan;
    }
}

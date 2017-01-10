package cloud.swiftnode.kspam.abstraction.deniable;

import cloud.swiftnode.kspam.abstraction.Deniable;
import cloud.swiftnode.kspam.abstraction.ExecuteDeniable;
import cloud.swiftnode.kspam.util.Lang;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * Created by Junhyeong Lim on 2017-01-10.
 */
public class LoginEventDeniable extends ExecuteDeniable {
    private PlayerLoginEvent event;

    public LoginEventDeniable(boolean async, PlayerLoginEvent event) {
        super(async);
        this.event = event;
    }

    @Override
    public void executeDeny() {
        event.disallow(PlayerLoginEvent.Result.KICK_BANNED, Lang.DENY.toString());
    }
}

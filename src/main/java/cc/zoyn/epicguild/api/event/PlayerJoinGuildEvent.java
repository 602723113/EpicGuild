package cc.zoyn.epicguild.api.event;

import cc.zoyn.epicguild.dto.Guild;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Bukkit will call this event when the player joins the guild
 *
 * @author Zoyn
 * @since 2017-12-16
 */
public class PlayerJoinGuildEvent extends GuildEvent implements Cancellable {

    @Getter
    private final Player player;

    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;

    public PlayerJoinGuildEvent(Guild guild, Player player) {
        super(guild);
        this.player = player;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}

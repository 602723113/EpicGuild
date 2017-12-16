package cc.zoyn.epicguild.api.event;


import cc.zoyn.epicguild.dto.Guild;
import lombok.Getter;
import org.bukkit.event.Event;

/**
 * @author Zoyn
 * @since 2017-12-16
 */
public abstract class GuildEvent extends Event {

    @Getter
    protected Guild guild;

    public GuildEvent(Guild guild) {
        this.guild = guild;
    }

    GuildEvent(Guild guild, boolean async) {
        super(async);
        this.guild = guild;
    }


}

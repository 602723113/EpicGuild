package cc.zoyn.epicguild.manager;

import cc.zoyn.epicguild.dto.Guild;
import com.google.common.collect.Lists;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author Zoyn
 * @since 2017-11-13
 */
public class GuildManager implements IGuildManager {

    private static final List<Guild> guildList = Lists.newArrayList();

    @Override
    public GuildManager addGuild(@Nonnull Guild guild) {
        guildList.add(guild);
        return this;
    }

    @Override
    public GuildManager removeGuild(@Nonnull Guild guild) {
        if (guildList.contains(guild)) {
            guildList.remove(guild);
        }
        return this;
    }

    @Override
    public Guild getGuildByPlayer(Player player) {
        return guildList.stream()
                .filter(guild -> guild.isMember(player))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Guild getGuildByName(String guildName) {
        return guildList.stream()
                .filter(guild -> guild.getName().equals(guildName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Guild> getGuilds() {
        return guildList;
    }
}

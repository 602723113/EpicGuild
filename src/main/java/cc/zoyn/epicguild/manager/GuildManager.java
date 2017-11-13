package cc.zoyn.epicguild.manager;

import cc.zoyn.epicguild.dto.Guild;
import com.google.common.collect.Lists;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author Zoyn
 * @since 2017-11-13
 */
public class GuildManager implements IGuildManager {

    private static List<Guild> guildList = Lists.newArrayList();

    @Override
    public GuildManager addGuild(Guild guild) {
        return null;
    }

    @Override
    public GuildManager removeGuild(Guild guild) {
        return null;
    }

    @Override
    public Guild getGuildByPlayer(Player player) {
        return null;
    }

    @Override
    public Guild getGuildByName(String guildName) {
        return null;
    }

    @Override
    public List<Guild> getGuilds() {
        return null;
    }
}

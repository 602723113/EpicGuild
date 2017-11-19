package cc.zoyn.epicguild.manager;

import cc.zoyn.epicguild.dto.Guild;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author Zoyn
 * @since 2017-11-13
 */
public interface IGuildManager {


    GuildManager addGuild(Guild guild);

    GuildManager removeGuild(Guild guild);

    Guild getGuildByPlayer(Player player);

    Guild getGuildByName(String guildName);

    List<Guild> getGuilds();

    List<Guild> loadGuilds();

    void saveGuilds();

}

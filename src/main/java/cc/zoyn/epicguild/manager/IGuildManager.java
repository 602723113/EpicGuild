package cc.zoyn.epicguild.manager;

import cc.zoyn.epicguild.dto.Guild;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author Zoyn
 * @since 2017-11-13
 */
public interface IGuildManager {

    Guild createGuild(String owner, String name, String description, int level, int maxPeople, double money);

    GuildManager addGuild(Guild guild);

    GuildManager removeGuild(Guild guild);

    Guild getGuildByPlayer(Player player);

    Guild getGuildByName(String guildName);

    List<Guild> getGuilds();

    List<Guild> loadGuilds();

    void saveGuilds();

}

package cc.zoyn.epicguild.dao;

import cc.zoyn.epicguild.dto.Guild;

import java.util.List;
import java.util.Optional;

/**
 * @author Zoyn
 * @since 2017-11-20
 */
public interface EpicGuildDao {

    Optional<Guild> getGuild(String guildName);

    List<Guild> listGuild();

    boolean insertGuild(Guild guild);

    boolean deleteGuild(String guildName);

    int countGuild();

    boolean updateGuild(Guild guild);

}

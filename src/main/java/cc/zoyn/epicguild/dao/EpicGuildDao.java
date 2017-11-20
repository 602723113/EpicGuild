package cc.zoyn.epicguild.dao;

import cc.zoyn.epicguild.dto.Guild;

import java.util.List;

/**
 * @author Zoyn
 * @since 2017-11-20
 */
public interface EpicGuildDao {

    Guild getGuild(String guildName);

    List<Guild> listGuild();

    void insertGuild(Guild guild);

    void saveGuild(Guild guild);

    void deleteGuild(String guildName);

    int countGuild(String playerName);

    void updateGuild(Guild guild);

}

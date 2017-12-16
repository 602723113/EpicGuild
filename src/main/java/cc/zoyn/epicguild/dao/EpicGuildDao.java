package cc.zoyn.epicguild.dao;

import cc.zoyn.epicguild.dto.Guild;

import java.util.List;
import java.util.Optional;

/**
 * @author Zoyn
 * @since 2017-11-20
 */
public interface EpicGuildDao {

    Optional<Guild> getGuildById(int id);

    Optional<Guild> getGuildByGuildName(String guildName);

    Optional<Guild> getGuildByOwnerName(String ownerName);

    List<Guild> listGuild(int page, int amount);

    boolean insertGuild(Guild guild);

    boolean deleteGuild(String guildName);

    int countGuild();

    boolean updateGuild(Guild guild);

    boolean updateOwnerNameByGuildName(String guildName, String ownerName);

    boolean updateMoneyByGuildName(String guildName, double money);

}

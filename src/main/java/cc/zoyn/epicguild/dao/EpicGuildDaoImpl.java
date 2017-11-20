package cc.zoyn.epicguild.dao;

import cc.zoyn.epicguild.dto.Guild;

import java.util.List;

/**
 * @author Zoyn
 * @since 2017-11-20
 */
public class EpicGuildDaoImpl implements EpicGuildDao {

    private static EpicGuildDao instance;

    private EpicGuildDaoImpl() {}

    public static EpicGuildDao getInstance() {
        if (instance == null) {
            instance = new EpicGuildDaoImpl();
        }
        return instance;
    }

    @Override
    public Guild getGuild(String guildName) {
        return null;
    }

    @Override
    public List<Guild> listGuild() {
        return null;
    }

    @Override
    public void insertGuild(Guild guild) {

    }

    @Override
    public void saveGuild(Guild guild) {

    }

    @Override
    public void deleteGuild(String guildName) {

    }

    @Override
    public int countGuild(String playerName) {
        return 0;
    }

    @Override
    public void updateGuild(Guild guild) {

    }
}

package cc.zoyn.epicguild.dao;

import cc.zoyn.epicguild.EpicGuild;
import cc.zoyn.epicguild.dto.Guild;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * @author Zoyn
 * @since 2017-11-20
 */
public class EpicGuildDaoImpl implements EpicGuildDao {

    private static EpicGuildDao instance;
    private DatabaseManager databaseManager = EpicGuild.getInstance().getDatabaseManager();
    private String tablePrefix = databaseManager.getTablePrefix();

    private EpicGuildDaoImpl() {
    }

    public static EpicGuildDao getInstance() {
        if (instance == null) {
            instance = new EpicGuildDaoImpl();
        }
        return instance;
    }

    @Override
    public Optional<Guild> getGuild(String guildName) {
        String sql = "SELECT FROM " + tablePrefix + "guilds WHERE guildName = '?' LIMIT 1";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Optional<Guild> optional = Optional.empty();

        try {
            preparedStatement = databaseManager.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, guildName);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                optional = Optional.of(
                        new Guild(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getInt(2),
                                resultSet.getInt(3),
                                resultSet.getDouble(1)
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return optional;
    }

    @Override
    public List<Guild> listGuild() {
        return null;
    }

    @Override
    public boolean insertGuild(Guild guild) {
        String sql = "INSERT INTO " + tablePrefix + "guilds VALUES(null, '?', '?', '?', '?', '?', '?')";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = databaseManager.getConnection().prepareStatement(sql);
            preparedStatement.setString(2, guild.getName());
            preparedStatement.setString(3, guild.getOwner());
            preparedStatement.setInt(4, guild.getMaxPeople());
            preparedStatement.setInt(5, guild.getLevel());
            preparedStatement.setDouble(6, guild.getMoney());
            preparedStatement.setLong(7, guild.getCreateTime().getTime());

            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteGuild(String guildName) {
        String sql = "DELETE FROM " + tablePrefix + "guilds WHERE guild_name = '?'";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = databaseManager.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, guildName);

            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int countGuild() {
        int count = 0;
        String sql = "SELECT count(*) AS COUNT FROM " + tablePrefix + "guilds";
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            preparedStatement = databaseManager.getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public boolean updateGuild(Guild guild) {
        return false;
    }
}

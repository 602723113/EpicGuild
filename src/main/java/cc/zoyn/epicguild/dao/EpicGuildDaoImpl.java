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

    private EpicGuildDaoImpl() {
    }

    public static EpicGuildDao getInstance() {
        if (instance == null) {
            instance = new EpicGuildDaoImpl();
        }
        return instance;
    }

    @Override
    public Optional<Guild> getGuildById(int id) {
        Optional<Guild> optional = Optional.empty();
        return optional;
    }

    @Override
    public Optional<Guild> getGuildByGuildName(String guildName) {
        String sql = "SELECT * FROM 'eg_guilds' WHERE 'guildName' = '?' LIMIT 1";
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
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getInt(5),
                                resultSet.getInt(6),
                                resultSet.getDouble(7)
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return optional;
    }

    @Override
    public Optional<Guild> getGuildByOwnerName(String ownerName) {
        Optional<Guild> optional = Optional.empty();
        return optional;
    }

    @Override
    public List<Guild> listGuild() {
        return null;
    }

    @Override
    public boolean insertGuild(Guild guild) {
        String sql = "INSERT INTO 'eg_guilds' VALUES(NULL , '?', '?', '?', '?', '?', '?', '?')";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = databaseManager.getConnection().prepareStatement(sql);
            preparedStatement.setString(2, guild.getOwner());
            preparedStatement.setString(3, guild.getName());
            preparedStatement.setString(4, guild.getDescription());
            preparedStatement.setInt(5, guild.getLevel());
            preparedStatement.setInt(6, guild.getMaxPeople());
            preparedStatement.setDouble(7, guild.getMoney());
            preparedStatement.setLong(8, guild.getCreateTime().getTime());

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
        String sql = "DELETE FROM 'eg_guilds' WHERE guild_name = '?'";
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
        String sql = "SELECT count(*) AS COUNT FROM 'eg_guilds'";
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
        String sql = "UPDATE 'eg_guilds' SET owner_name = '?', guild_name = '?', description = '?', level = '?', max_player = '?', money = '?', create_time = '?'";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = databaseManager.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, guild.getOwner());
            preparedStatement.setString(2, guild.getName());
            preparedStatement.setString(3, guild.getDescription());
            preparedStatement.setInt(4, guild.getLevel());
            preparedStatement.setInt(5, guild.getMaxPeople());
            preparedStatement.setDouble(6, guild.getMoney());
            preparedStatement.setLong(7, guild.getCreateTime().getTime());

            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateOwnerNameByGuildName(String guildName, String ownerName) {
        return false;
    }

    @Override
    public boolean updateLevelByGuildName(String guildName, int level) {
        return false;
    }

    @Override
    public boolean updateMaxPeopleByGuildName(String guildName, int maxPeople) {
        return false;
    }

    @Override
    public boolean updateMoneyByGuildName(String guildName, double money) {
        return false;
    }
}

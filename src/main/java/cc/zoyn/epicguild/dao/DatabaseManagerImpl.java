package cc.zoyn.epicguild.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimeZone;

/**
 * @author Zoyn
 * @since 2017-11-19
 */
@Getter
public class DatabaseManagerImpl implements DatabaseManager {

    private String host = null;
    private int port = 3306;
    private String userName = "root";
    private String password = "";
    private String database = null;
    private HikariDataSource dataSource = null;

    public DatabaseManagerImpl(String host, int port, String userName, String password, String database, String tablePrefix) {
        this.host = host;
        this.port = port;
        this.userName = userName;
        this.password = password;
        this.database = database;
    }

    @Override
    public void initialize() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + this.host + ":" + String.valueOf(this.port) + "/" + this.database + "?autoReconnect=true&serverTimezone=" + TimeZone.getDefault().getID());
        config.setUsername(this.userName);
        config.setPassword(this.password);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        this.dataSource = new HikariDataSource(config);

        try {
            getConnection().prepareStatement(getCreateTableSQL()).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Bukkit.getLogger().info("[EpicGuild] initialize database successfully!");
    }

    @Override
    public Connection getConnection() {
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean connectionIsClose() {
        return this.dataSource.isClosed();
    }

    @Override
    public void closeConnection() {
        dataSource.close();
    }

    private String getCreateTableSQL() {
        return "CREATE TABLE IF NOT EXISTS eg_guilds " +
                "(id INT AUTO_INCREMENT PRIMARY KEY," +
                "owner_name CHAR(32) NOT NULL," +
                "guild_name CHAR(64) NOT NULL," +
                "description TEXT NOT NULL," +
                "level INT NOT NULL," +
                "max_player INT NOT NULL," +
                "money DOUBLE NOT NULL," +
                "create_time LONG NOT NULL);";
    }
}

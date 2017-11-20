package cc.zoyn.epicguild.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;

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
    private String userName;
    private String password = null;
    private String database = null;
    private String tablePrefix = "eg_";
    private HikariDataSource dataSource = null;

    public DatabaseManagerImpl(String host, int port, String userName, String password, String database, String tablePrefix) {
        this.host = host;
        this.port = port;
        this.userName = userName;
        this.password = password;
        this.database = database;
        this.tablePrefix = tablePrefix;
    }

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
            System.out.println("execute");
            getConnection().prepareStatement(getCreateTableSQL()).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getCreateTableSQL() {
        StringBuilder builder = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        builder.append(this.tablePrefix)
                .append("guilds (id INT AUTO_INCREMENT PRIMARY KEY,")
                .append("guild_name TEXT NOT NULL,")
                .append("owner_name TEXT NOT NULL,")
                .append("max_player INT NOT NULL,")
                .append("level INT NOT NULL,")
                .append("money DOUBLE NOT NULL,")
                .append("create_time DATETIME NOT NULL);");
        return builder.toString();
    }

    public boolean isClose() {
        return this.dataSource.isClosed();
    }
}

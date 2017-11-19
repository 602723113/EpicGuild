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
public class DatabaseManager {

    private String tablePrefix = "eg_";
    private String database = null;
    private String host = null;
    private int port = 3306;
    private String userName;
    private String password = null;
    private HikariDataSource dataSource = null;

    public DatabaseManager(String host, int port, String userName, String password, String database, String tablePrefix) {
        this.host = host;
        this.port = port;
        this.userName = userName;
        this.password = password;
        this.database = database;
        this.tablePrefix = tablePrefix;
    }

    public void initialize() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + this.host + ":" + String.valueOf(this.port) + "/"
                + this.database + "?autoReconnect=true&serverTimezone=" + TimeZone
                .getDefault().getID());
        config.setUsername(this.userName);
        config.setPassword(this.password);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        this.dataSource = new HikariDataSource(config);
    }

    public Connection getConnection() {
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

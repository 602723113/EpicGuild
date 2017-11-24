package cc.zoyn.epicguild.dao;

import java.sql.Connection;

/**
 * @author Zoyn
 * @since 2017-11-19
 */
public interface DatabaseManager {

    /**
     * 初始化数据库方法
     */
    void initialize();

    Connection getConnection();

    boolean connectionIsClose();

    void closeConnection();

    String getTablePrefix();

}

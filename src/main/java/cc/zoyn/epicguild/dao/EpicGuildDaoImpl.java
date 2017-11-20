package cc.zoyn.epicguild.dao;

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

}

package cc.zoyn.epicguild;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main Class
 *
 * @author Zoyn
 * @since 2017-11-12
 */
public class EpicGuild extends JavaPlugin {

    private static EpicGuild instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    /**
     * 获取{@link EpicGuild}的实例
     *
     * @return {@link EpicGuild}
     */
    public static EpicGuild getInstance() {
        return instance;
    }

}

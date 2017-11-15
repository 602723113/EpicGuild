package cc.zoyn.epicguild;

import cc.zoyn.epicguild.command.CommandManager;
import cc.zoyn.epicguild.dto.Apply;
import cc.zoyn.epicguild.dto.Guild;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
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
        saveDefaultConfig();
        Bukkit.getPluginCommand("epicguild").setExecutor(new CommandManager());

        ConfigurationSerialization.registerClass(Apply.class);
        ConfigurationSerialization.registerClass(Guild.class);
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

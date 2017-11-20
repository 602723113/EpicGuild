package cc.zoyn.epicguild;

import cc.zoyn.epicguild.command.CommandManagerImpl;
import cc.zoyn.epicguild.dao.DatabaseManager;
import cc.zoyn.epicguild.dto.Apply;
import cc.zoyn.epicguild.dto.DataStorageType;
import cc.zoyn.epicguild.dto.Guild;
import cc.zoyn.epicguild.manager.ConfigManager;
import cc.zoyn.epicguild.manager.GuildManagerImpl;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * Main Class
 *
 * @author Zoyn
 * @since 2017-11-12
 */
@Getter
public class EpicGuild extends JavaPlugin {

    private static EpicGuild instance;
    private File guildDataFile;
    private DatabaseManager databaseManager = null;

    @Override
    public void onEnable() {
        instance = this;

        // save yml
        saveDefaultConfig();
        saveResource("items.yml", false);
        guildDataFile = new File(getDataFolder(), "/Guilds");
        if (!guildDataFile.exists()) {
            guildDataFile.mkdirs();
        }

        // register some things..
        Bukkit.getPluginCommand("epicguild").setExecutor(new CommandManagerImpl());
        ConfigurationSerialization.registerClass(Apply.class);
        ConfigurationSerialization.registerClass(Guild.class);

        // loading guilds
        GuildManagerImpl.getInstance().loadGuilds();

        ConfigManager.getEnumByDefault("", DataStorageType.YAML);

//        databaseManager = new DatabaseManagerImpl();
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

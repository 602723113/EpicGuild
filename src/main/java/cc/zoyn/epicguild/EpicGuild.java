package cc.zoyn.epicguild;

import cc.zoyn.epicguild.command.CommandManagerImpl;
import cc.zoyn.epicguild.dao.DatabaseManager;
import cc.zoyn.epicguild.dto.Apply;
import cc.zoyn.epicguild.dto.Guild;
import cc.zoyn.epicguild.hook.PlaceHolderAPIHook;
import cc.zoyn.epicguild.manager.GuildManagerImpl;
import cc.zoyn.epicguild.runnable.SaveDataRunnable;
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

    /**
     * 保存数据线程
     */
    private SaveDataRunnable saveDataRunnable;

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

        // check storage type
//        String storageType = ConfigManager.getStringByDefault("EpicGuildOptions.DataStorageType", "YAML", false);
//        if (DataStorageType.getByName(storageType).equals(DataStorageType.MySQL)) {
//
//            String host = ConfigManager.getStringByDefault("DatabaseOptions.host", "localhost", false);
//            int port = ConfigManager.getIntByDefault("DatabaseOptions.port", 3306);
//            String user = ConfigManager.getStringByDefault("DatabaseOptions.user", "root", false);
//            String password = ConfigManager.getStringByDefault("DatabaseOptions.password", "", false);
//            String database = ConfigManager.getStringByDefault("DatabaseOptions.database", "mc", false);
//            String tablePrefix = ConfigManager.getStringByDefault("DatabaseOptions.tableprefix", "eg_", false);
//
//            databaseManager = new DatabaseManagerImpl(host, port, user, password, database, tablePrefix);
//            databaseManager.initialize();
//
//            EpicGuildDaoImpl.getInstance().countGuild();
//        }


        // loading guilds
        GuildManagerImpl.getInstance().loadGuilds();

        // add PlaceholderAPI support
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new PlaceHolderAPIHook().hook();
        }

        saveDataRunnable = new SaveDataRunnable();
        saveDataRunnable.runTaskTimerAsynchronously(this, 20L, 10 * 60 * 20L);
    }

    @Override
    public void onDisable() {
        saveDataRunnable.cancel();
    }

    /**
     * 获取 {@link EpicGuild} 的实例
     *
     * @return {@link EpicGuild}
     */
    public static EpicGuild getInstance() {
        return instance;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

}

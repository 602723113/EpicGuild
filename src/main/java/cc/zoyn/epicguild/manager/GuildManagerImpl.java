package cc.zoyn.epicguild.manager;

import cc.zoyn.epicguild.EpicGuild;
import cc.zoyn.epicguild.dto.Guild;
import cc.zoyn.epicguild.util.ConfigurationUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.Validate;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.io.File;
import java.util.List;
import java.util.Objects;

/**
 * @author Zoyn
 * @since 2017-11-13
 */
public class GuildManagerImpl implements GuildManager {

    private static GuildManager instance = new GuildManagerImpl();
    private static final List<Guild> guildList = Lists.newArrayList();

    public static GuildManager getInstance() {
        if (instance == null) {
            instance = new GuildManagerImpl();
        }
        return instance;
    }

    @Override
    public Guild createGuild(String owner, String name, String description, int level, int maxPeople, double money) {
        return new Guild(owner, name, description, level, maxPeople, money);
    }

    @Override
    public GuildManagerImpl addGuild(@Nullable Guild guild) {
        Validate.notNull(guild);

        guildList.add(guild);
        return this;
    }

    @Override
    public GuildManagerImpl removeGuild(@Nullable Guild guild) {
        Validate.notNull(guild);

        if (guildList.contains(guild)) {
            guildList.remove(guild);
        }
        return this;
    }

    @Override
    public Guild getGuildByPlayer(@Nullable Player player) {
        Validate.notNull(player);

        return guildList.stream()
                .filter(guild -> guild.isMember(player))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Guild getGuildByName(@Nullable String guildName) {
        Validate.notNull(guildName);

        return guildList.stream()
                .filter(guild -> guild.getName().equals(guildName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Guild> getGuilds() {
        return guildList;
    }

    @Override
    public List<Guild> loadGuilds() {
        guildList.clear();
        File dataFile = EpicGuild.getInstance().getGuildDataFile();
        if (dataFile == null) {
            Bukkit.getLogger().warning("GuildDataFolder is missing!");
            return guildList;
        }

        FileConfiguration fileConfiguration;
        Guild guild;

        for (File file : Objects.requireNonNull(dataFile.listFiles())) {
            fileConfiguration = ConfigurationUtils.loadYml(file);
            if (fileConfiguration.contains("Guild")) {
                guild = (Guild) fileConfiguration.get("Guild");
                guildList.add(guild);
            }
        }

        EpicGuild.getInstance().getLogger().info("Load guilds successfully");
        return guildList;
    }

    @Override
    public void saveGuilds() {
        if (!guildList.isEmpty()) {
            // 遍历list储存数据
            guildList.forEach(this::saveGuild);
        }
    }

    /**
     * 储存单个Guild对象
     *
     * @param guild 公会对象
     */
    public void saveGuild(Guild guild) {
        Validate.notNull(guild);

        File file = new File(EpicGuild.getInstance().getGuildDataFile(), guild.getName() + ".yml");
        FileConfiguration fileConfiguration = ConfigurationUtils.loadYml(file);
        fileConfiguration.set("Guild", guild);
        ConfigurationUtils.saveYml(fileConfiguration, file);
    }
}

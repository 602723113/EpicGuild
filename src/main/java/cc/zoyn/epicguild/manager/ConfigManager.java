package cc.zoyn.epicguild.manager;

import cc.zoyn.epicguild.EpicGuild;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Be used for manage config
 *
 * @author Zoyn
 * @since 2017-11-11
 */
public class ConfigManager {

    private static FileConfiguration config = EpicGuild.getInstance().getConfig();

    /**
     * 你不能实例化ConfigManager
     */
    private ConfigManager() {
    }

    /**
     * 取当前Config的FileConfiguration对象
     *
     * @return {@link FileConfiguration}
     */
    public static FileConfiguration getConfig() {
        return config;
    }

    public static boolean getBooleanByDefault(String key, boolean defaultValue) {
        if (config.contains(key) && config.isBoolean(key)) {
            return config.getBoolean(key);
        }
        return defaultValue;
    }

    /**
     * 取config内的StringList
     *
     * @param key          键名
     * @param defaultValue 默认值
     * @param translate    是否转换颜色字符代码
     * @return {@link List}
     */
    public static List<String> getStringListByDefault(String key, List<String> defaultValue, boolean translate) {
        if (config.contains(key) && config.isList(key) && !config.getList(key).isEmpty()) {
            if (translate) {
                return config.getStringList(key)
                        .stream()
                        .map(
                                s -> ChatColor.translateAlternateColorCodes('&', s)
                        )
                        .collect(Collectors.toList());
            } else {
                return config.getStringList(key);
            }
        }
        if (translate) {
            return defaultValue.stream().map(s -> ChatColor.translateAlternateColorCodes('&', s)).collect(Collectors.toList());
        } else {
            return defaultValue;
        }
    }

    /**
     * 取config内的String
     *
     * @param key          键名
     * @param defaultValue 默认值
     * @param translate    是否转换颜色字符代码
     * @return {@link String}
     */
    public static String getStringByDefault(String key, String defaultValue, boolean translate) {
        if (config.contains(key) && config.isString(key)) {
            if (translate) {
                return ChatColor.translateAlternateColorCodes('&', config.getString(key));
            } else {
                return config.getString(key);
            }
        }
        if (translate) {
            return ChatColor.translateAlternateColorCodes('&', defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static int getIntByDefault(String key, int defaultValue) {
        if (config.contains(key) && config.isInt(key)) {
            return config.getInt(key);
        }
        return defaultValue;
    }

    public static double getDoubleByDefault(String key, double defaultValue) {
        if (config.contains(key) && config.isDouble(key)) {
            return config.getDouble(key);
        }
        return defaultValue;
    }

    public static long getLongByDefault(String key, long defaultValue) {
        if (config.contains(key) && config.isLong(key)) {
            return config.getLong(key);
        }
        return defaultValue;
    }

}

package cc.zoyn.epicguild.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.DumperOptions;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Easy to use yml
 *
 * @author Zoyn
 * @since 2017-11-19
 */
public final class ConfigurationUtils {

    private ConfigurationUtils() {}

    /**
     * 用路径读取Yml
     *
     * @param path 路径
     * @return {@link FileConfiguration}
     */
    public static FileConfiguration loadYml(String path) {
        File file = new File(path);
        return loadYml(file);
    }

    /**
     * 保存Yml
     *
     * @param fileConfiguration 该Yml的FileConfiguration对象
     * @param file              文件
     */
    public static void saveYml(FileConfiguration fileConfiguration, File file) {
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取Yml
     *
     * @param file 文件
     * @return {@link FileConfiguration}
     */
    public static FileConfiguration loadYml(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("错误:" + e.toString());
            }
        }
        FileConfiguration YML = YamlConfiguration.loadConfiguration(file);
        DumperOptions yamlOptions = null;
        try {
            Field f = YamlConfiguration.class.getDeclaredField("yamlOptions");
            f.setAccessible(true);
            yamlOptions = new DumperOptions() {
                public void setAllowUnicode(boolean allowUnicode) {
                    super.setAllowUnicode(false);
                }

                public void setLineBreak(DumperOptions.LineBreak lineBreak) {
                    super.setLineBreak(DumperOptions.LineBreak.getPlatformLineBreak());
                }
            };
            yamlOptions.setLineBreak(DumperOptions.LineBreak.getPlatformLineBreak());
            f.set(YML, yamlOptions);
        } catch (ReflectiveOperationException ex) {
            ex.printStackTrace();
        }
        return YML;
    }
}

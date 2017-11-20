package cc.zoyn.epicguild.api;

import cc.zoyn.epicguild.manager.GuildManager;
import cc.zoyn.epicguild.manager.GuildManagerImpl;
import cc.zoyn.epicguild.util.ActionBarUtils;
import cc.zoyn.epicguild.util.TitleUtils;
import org.bukkit.entity.Player;

/**
 * @author Zoyn
 * @since 2017-11-13
 */
public class EpicGuildAPI {

    public static GuildManager getGuildManager() {
        return GuildManagerImpl.getInstance();
    }

    public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subTitle) {
        TitleUtils.sendTitle(player, fadeIn, stay, fadeOut, title, subTitle);
    }

    public static void sendActionbar(Player player, String msg) {
        ActionBarUtils.sendBar(player, msg);
    }

}

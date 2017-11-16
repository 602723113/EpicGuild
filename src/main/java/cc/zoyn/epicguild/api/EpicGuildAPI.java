package cc.zoyn.epicguild.api;

import cc.zoyn.epicguild.manager.GuildManager;

/**
 * @author Zoyn
 * @since 2017-11-13
 */
public class EpicGuildAPI {

    public static GuildManager getGuildManager() {
        return GuildManager.getInstance();
    }
}

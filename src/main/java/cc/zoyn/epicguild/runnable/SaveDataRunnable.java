package cc.zoyn.epicguild.runnable;

import cc.zoyn.epicguild.api.EpicGuildAPI;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Zoyn
 * @since 2017-12-16
 */
public class SaveDataRunnable extends BukkitRunnable {

    @Override
    public void run() {
        EpicGuildAPI.getGuildManager().saveGuilds();
    }

}

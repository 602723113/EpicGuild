package cc.zoyn.epicguild.runnable;

import cc.zoyn.epicguild.EpicGuild;
import cc.zoyn.epicguild.api.EpicGuildAPI;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * 数据保存线程
 *
 * @author Zoyn
 * @since 2017-12-16
 */
public class SaveDataRunnable extends BukkitRunnable {

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        EpicGuildAPI.getGuildManager().saveGuilds();
        EpicGuild.getInstance().getLogger().info("正在进行数据保存 耗时 " + (System.currentTimeMillis() - start) + " ms");
    }

}

package cc.zoyn.epicguild.util;

import org.bukkit.command.CommandSender;

/**
 * 用于表示一个子命令
 *
 * @author Zoyn
 * @since 2017-11-12
 */
public interface SubCommand {

    void execute(CommandSender sender, String[] args);

}

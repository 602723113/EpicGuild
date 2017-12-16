package cc.zoyn.epicguild.util;

import org.bukkit.command.CommandSender;

/**
 * Represent a sub command
 *
 * @author Zoyn
 * @since 2017-11-12
 */
public interface SubCommand {

    void execute(CommandSender sender, String[] args);

}

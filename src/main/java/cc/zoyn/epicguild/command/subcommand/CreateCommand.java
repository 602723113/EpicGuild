package cc.zoyn.epicguild.command.subcommand;

import cc.zoyn.epicguild.util.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * 创建公会指令
 *
 * @author Zoyn
 * @since 2017-11-12
 */
public class CreateCommand implements SubCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            sender.sendMessage("create");
        }
    }

}

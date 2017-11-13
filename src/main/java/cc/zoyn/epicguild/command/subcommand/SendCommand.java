package cc.zoyn.epicguild.command.subcommand;

import cc.zoyn.epicguild.api.EpicGuildAPI;
import cc.zoyn.epicguild.util.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Zoyn
 * @since 2017-11-13
 */
public class SendCommand implements SubCommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            EpicGuildAPI.getGuildByPlayer(player);
        }
    }
}

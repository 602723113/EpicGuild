package cc.zoyn.epicguild.command.subcommand;

import cc.zoyn.epicguild.manager.GuildManager;
import cc.zoyn.epicguild.util.SubCommand;
import org.bukkit.command.CommandSender;

/**
 * @author Zoyn
 * @since 2017-11-15
 */
public class ListCommand implements SubCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("§6[§eEpicGuild§6]§7----------------------");
        sender.sendMessage(" ");
        sender.sendMessage("公会数量: " + GuildManager.getInstance().getGuilds().size());
        sender.sendMessage("");


    }
}

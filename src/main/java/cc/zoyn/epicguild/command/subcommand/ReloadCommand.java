package cc.zoyn.epicguild.command.subcommand;

import cc.zoyn.epicguild.EpicGuild;
import cc.zoyn.epicguild.manager.ConfigManager;
import cc.zoyn.epicguild.util.SubCommand;
import org.bukkit.command.CommandSender;

/**
 * 重载插件指令
 *
 * @author Zoyn
 * @since 2017-11-12
 */
public class ReloadCommand implements SubCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.isOp()) {
            EpicGuild.getInstance().reloadConfig();
            sender.sendMessage(ConfigManager.getStringByDefault("CommandMessage.Reload", "&a重载成功!", true));
        }
    }

}

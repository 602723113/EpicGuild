package cc.zoyn.epicguild.command.subcommand;

import cc.zoyn.epicguild.dto.Apply;
import cc.zoyn.epicguild.dto.Guild;
import cc.zoyn.epicguild.manager.ConfigManager;
import cc.zoyn.epicguild.manager.GuildManager;
import cc.zoyn.epicguild.util.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;

/**
 * @author Zoyn
 * @since 2017-11-19
 */
public class JoinCommand implements SubCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {

            // get guild object
            Guild guild = GuildManager.getInstance().getGuildByName(args[1]);
            if (guild == null) {
                sender.sendMessage(ConfigManager.getStringByDefault("CommandMessage.MissingGuild", "&6[&eEpicGuild&6] &c公会不存在!", true));
                return;
            }

            // create apply obj
            Apply apply = Apply.createApply(sender.getName(), new Date());
            guild.addApply(apply);
        } else {
            sender.sendMessage(ConfigManager.getStringByDefault("CommandMessage.NotAPlayer", "&6[&eEpicGuild&6] &c你必须是一名玩家!", true));
        }
    }
}

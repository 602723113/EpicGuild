package cc.zoyn.epicguild.command.subcommand;

import cc.zoyn.epicguild.dto.Guild;
import cc.zoyn.epicguild.manager.ConfigManager;
import cc.zoyn.epicguild.manager.GuildManager;
import cc.zoyn.epicguild.util.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Zoyn
 * @since 2017-11-19
 */
public class LeaveCommand implements SubCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Guild guild = GuildManager.getInstance().getGuildByPlayer(player);
            if (guild == null) {
                player.sendMessage(ConfigManager.getStringByDefault("CommandMessage.PlayerDontHaveGuild", "&6[&eEpicGuild&6] &c你没有公会!", true));
                return;
            }
            guild.removeMember(player);
            player.sendMessage(ConfigManager.getStringByDefault("CommandMessage.LeaveSuccess", "&6[&eEpicGuild&6] &a退出公会成功!", true));
        } else {
            sender.sendMessage(ConfigManager.getStringByDefault("CommandMessage.NotAPlayer", "&6[&eEpicGuild&6] &c你必须是一名玩家!", true));
        }
    }
}

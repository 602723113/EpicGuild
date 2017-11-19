package cc.zoyn.epicguild.command.subcommand;

import cc.zoyn.epicguild.dto.Guild;
import cc.zoyn.epicguild.manager.ConfigManager;
import cc.zoyn.epicguild.manager.GuildManager;
import cc.zoyn.epicguild.util.SubCommand;
import org.bukkit.ChatColor;
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

            Guild guild = GuildManager.getInstance().getGuildByPlayer(player);
            if (guild != null) {
                guild.sendMessage(ChatColor.translateAlternateColorCodes('&', args[1]));
            } else {
                player.sendMessage(ConfigManager.getStringByDefault("CommandMessage.PlayerDontHaveGuild", "&6[&eEpicGuild&6] &c你没有公会!", true));
            }
        }
    }
}

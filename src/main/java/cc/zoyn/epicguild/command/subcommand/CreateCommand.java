package cc.zoyn.epicguild.command.subcommand;

import cc.zoyn.epicguild.dto.Guild;
import cc.zoyn.epicguild.manager.ConfigManager;
import cc.zoyn.epicguild.manager.GuildManager;
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
            if (args.length != 2) {
                sender.sendMessage(ConfigManager.getStringByDefault("CommandMessage.CommandLengthWrong", "&6[&eEpicGuild&6] &c指令用法不正确! 请输入 &e/guild help &c查看帮助.", true));
                return;
            }
            Player player = (Player) sender;
            Guild guild = new Guild(player.getName(), args[1], "§7这个公会很懒什么介绍也没填", 1, ConfigManager.getIntByDefault("GuildOptions.MaxPeople.1", 10), 0);
            GuildManager.getInstance().addGuild(guild);
            player.sendMessage(ConfigManager.getStringByDefault("CommandMessage.CreateSuccess", "&6[&eEpicGuild&6] &a成功创建公会&f&l[ &6%guild_name% &f&l]", true).replaceAll("%guild_name%", guild.getName()));
        }
    }

}

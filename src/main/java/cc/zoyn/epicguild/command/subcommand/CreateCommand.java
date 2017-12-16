package cc.zoyn.epicguild.command.subcommand;

import cc.zoyn.epicguild.api.EpicGuildAPI;
import cc.zoyn.epicguild.api.event.GuildCreateEvent;
import cc.zoyn.epicguild.dto.Guild;
import cc.zoyn.epicguild.manager.ConfigManager;
import cc.zoyn.epicguild.util.SubCommand;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 创建公会指令
 *
 * @author Zoyn
 * @since 2017-11-12
 */
public class CreateCommand implements SubCommand {

    private static final Pattern NAME_PATTERN = Pattern.compile(ConfigManager.getStringByDefault("EpicGuildOptions.NamePattern", "[a-zA-Z0-9\\u4e00-\\u9fa5]+", false));

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            if (args.length != 2) {
                sender.sendMessage(ConfigManager.getStringByDefault("CommandMessage.CommandLengthWrong", "&6[&eEpicGuild&6] &c指令用法不正确! 请输入 &e/guild help &c查看帮助.", true));
                return;
            }
            Player player = (Player) sender;

            // name check
            String guildName = args[1];
            Matcher matcher = NAME_PATTERN.matcher(guildName);
            if (!matcher.matches()) {
                player.sendMessage(ConfigManager.getStringByDefault("CommandMessage.CannotMatchRegex", "&6[&eEpicGuild&6] &c该公会名字无法使用!", true));
                return;
            }

            Guild guild = new Guild(player.getName(), args[1], "§7这个公会很懒什么介绍也没填", 1, ConfigManager.getIntByDefault("EpicGuildOptions.MaxPeople.1", 10), 0);

            // call event
            GuildCreateEvent event = new GuildCreateEvent(guild, player);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                return;
            }

            EpicGuildAPI.getGuildManager().addGuild(guild);
            player.sendMessage(PlaceholderAPI.setPlaceholders(player, ConfigManager.getStringByDefault("CommandMessage.CreateSuccess", "&6[&eEpicGuild&6] &a成功创建公会! &f&l[ &6%epicguild_guild_name% &f&l]", true)));

            // save guild
            EpicGuildAPI.getGuildManager().saveGuild(guild);
        } else {
            sender.sendMessage(ConfigManager.getStringByDefault("CommandMessage.NotAPlayer", "&6[&eEpicGuild&6] &c你必须是一名玩家!", true));
        }
    }

}
package cc.zoyn.epicguild.hook;

import cc.zoyn.epicguild.EpicGuild;
import cc.zoyn.epicguild.api.EpicGuildAPI;
import cc.zoyn.epicguild.dto.Guild;
import me.clip.placeholderapi.external.EZPlaceholderHook;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;

/**
 * PlaceholderAPI 绑定
 *
 * @author Zoyn
 * @since 2017-11-22
 */
public class PlaceHolderAPIHook extends EZPlaceholderHook {

    public PlaceHolderAPIHook() {
        super(EpicGuild.getInstance(), "epicguild");
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (player == null) {
            return "";
        }

        Guild guild = EpicGuildAPI.getGuildManager().getGuildByPlayer(player);
        if (identifier.equalsIgnoreCase("guild_name")) {
            if (guild == null) {
                return "";
            } else {
                return guild.getName();
            }
        } else if (identifier.equalsIgnoreCase("guild_level")) {
            return guild == null ? "0" : String.valueOf(guild.getLevel());
        } else if (identifier.equalsIgnoreCase("guild_members")) {
            return guild == null ? "0" : String.valueOf(guild.getMembers().size());
        } else if (identifier.equalsIgnoreCase("guild_owner")) {
            return guild == null ? "null" : String.valueOf(guild.getOwner());
        } else if (identifier.equalsIgnoreCase("guild_createtime")) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return guild == null ? "null" : String.valueOf(simpleDateFormat.format(guild.getCreateTime()));
        }

        return "";
    }
}

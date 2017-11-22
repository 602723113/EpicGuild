package cc.zoyn.epicguild.hook;

import cc.zoyn.epicguild.EpicGuild;
import cc.zoyn.epicguild.dto.Guild;
import cc.zoyn.epicguild.manager.GuildManagerImpl;
import me.clip.placeholderapi.external.EZPlaceholderHook;
import org.bukkit.entity.Player;

/**
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

        Guild guild = GuildManagerImpl.getInstance().getGuildByPlayer(player);
        if (identifier.equalsIgnoreCase("guild_name")) {
            if (guild == null) {
                return "";
            } else {
                return guild.getName();
            }
        } else if (identifier.equalsIgnoreCase("guild_level")) {
            return guild == null ? "0" : String.valueOf(guild.getLevel());
        }

        return "";
    }
}

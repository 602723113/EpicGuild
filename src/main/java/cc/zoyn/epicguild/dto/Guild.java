package cc.zoyn.epicguild.dto;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * 表示一个公会
 *
 * @author Zoyn
 * @since 2017-11-12
 */
@Data
@AllArgsConstructor
public class Guild {

    private String owner;
    private String name;
    private String description = "§7这个公会很懒什么介绍也没填";
    private int level;
    private int maxPeople;
    private List<String> members = Lists.newArrayList();
    private List<String> invited = Lists.newArrayList();

    public List<Player> getOnlineMembers() {
        List<Player> onlinePlayers = Lists.newArrayList();
        Player player;
        for (int i = 0; i < members.size(); i++) {
            player = Bukkit.getPlayerExact(members.get(i));
            if (player != null) {
                onlinePlayers.add(player);
            }
        }
        return onlinePlayers;
    }

    public void sendMessage(String msg) {
        getOnlineMembers().forEach(player -> sendMessage(ChatColor.translateAlternateColorCodes('&', msg)));
    }

    public void sendMessage(List<String> msg) {
        msg.forEach(this::sendMessage);
    }

    public void removeMemebr(String playerName) {
        if (members.contains(playerName)) {
            members.remove(playerName);
        }
    }

    public void removeMember(Player player) {
        if (members.contains(player.getName())) {
            members.remove(player.getName());
        }
    }

}

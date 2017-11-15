package cc.zoyn.epicguild.dto;

import cc.zoyn.epicguild.manager.ConfigManager;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

/**
 * 表示一个公会
 *
 * @author Zoyn
 * @since 2017-11-12
 */
@Data
@AllArgsConstructor
public final class Guild implements ConfigurationSerializable {

    /**
     * 公会所有者
     */
    private String owner;
    /**
     * 公会名
     */
    private String name;
    /**
     * 公会介绍
     */
    private String description = "§7这个公会很懒什么介绍也没填";
    /**
     * 公会等级
     */
    private int level = 1;
    /**
     * 公会最大人数
     */
    private int maxPeople;
    /**
     * 公会资金
     */
    private double money;
    /**
     * 公会成员集合
     */
    private List<String> members = Lists.newArrayList();
    /**
     * 申请加入公会的集合
     */
    private List<Apply> applies = Lists.newArrayList();

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

    public boolean isOwner(String playerName) {
        return this.owner.equalsIgnoreCase(playerName);
    }

    public boolean isOwner(Player player) {
        return this.owner.equalsIgnoreCase(player.getName());
    }

    public boolean isMember(String playerName) {
        return this.members.contains(playerName);
    }

    public boolean isMember(Player player) {
        return this.members.contains(player.getName());
    }

    public void removeMemebr(String playerName) {
        // check is owner
        if (!isOwner(playerName)) {
            if (this.members.contains(playerName)) {
                this.members.remove(playerName);
            }
        } else {
            Player player = Bukkit.getPlayerExact(playerName);
            if (player != null) {
                player.sendMessage(ConfigManager.getStringByDefault("CommandMessage.OwnerCantQuitGuild", "&6[&eEpicGuild6] &c会主不能退出公会", true));
            }

        }
    }

    public void removeMember(Player player) {
        if (this.members.contains(player.getName())) {
            this.members.remove(player.getName());
        }
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("Owner", this.owner);
        map.put("Name", this.name);
        map.put("Description", this.description);
        map.put("Level", this.level);
        map.put("MaxPeople", this.maxPeople);
        map.put("Money", this.money);
        map.put("Members", this.members);
        map.put("Applies", this.applies);
        return map;
    }

}

package cc.zoyn.epicguild.dto;

import cc.zoyn.epicguild.manager.ConfigManager;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 表示一个公会
 *
 * @author Zoyn
 * @since 2017-11-12
 */
@Data
@NoArgsConstructor
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
     * 建立时间
     */
    private Date createTime;
    /**
     * 公会成员集合
     */
    private List<String> members = Lists.newArrayList();
    /**
     * 申请加入公会的集合
     */
    private List<Apply> applies = Lists.newArrayList();

    public Guild(String owner, String name, String description, int level, int maxPeople, double money) {
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.level = level;
        this.maxPeople = maxPeople;
        this.money = money;
        this.createTime = new Date();
        this.members.add(owner);
    }

    public Guild(String owner, String name, String description, int level, int maxPeople, double money, List<String> members, List<Apply> applies) {
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.level = level;
        this.maxPeople = maxPeople;
        this.money = money;
        this.createTime = new Date();
        this.members = members;
        this.members.add(owner);
        this.applies = applies;
    }

    public List<Player> getOnlineMembers() {
        List<Player> onlinePlayers = Lists.newArrayList();

        members.forEach(
                s -> {
                    Player player = Bukkit.getPlayerExact(s);
                    if (player != null) {
                        onlinePlayers.add(player);
                    }
                }
        );
        return onlinePlayers;
    }

    public void sendMessage(String msg) {
        getOnlineMembers().forEach(player -> sendMessage(ChatColor.translateAlternateColorCodes('&', msg)));
    }

    public void sendMessage(List<String> msg) {
        msg.forEach(this::sendMessage);
    }

    public boolean isOwner(@Nullable String playerName) {
        Validate.notNull(playerName);

        return this.owner.equalsIgnoreCase(playerName);
    }

    public boolean isOwner(@Nullable Player player) {
        Validate.notNull(player);

        return this.owner.equalsIgnoreCase(player.getName());
    }

    public boolean isMember(@Nullable String playerName) {
        Validate.notNull(playerName);

        return this.members.contains(playerName);
    }

    public boolean isMember(@Nullable Player player) {
        Validate.notNull(player);

        return this.members.contains(player.getName());
    }

    public void removeMember(@Nullable String playerName) {
        Validate.notNull(playerName);

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

    public void removeMember(@Nullable Player player) {
        Validate.notNull(player);

        if (!isOwner(player.getName())) {
            if (this.members.contains(player.getName())) {
                this.members.remove(player.getName());
            }
        } else {
            player.sendMessage(ConfigManager.getStringByDefault("CommandMessage.OwnerCantQuitGuild", "&6[&eEpicGuild6] &c会主不能退出公会", true));
        }
    }

    public void addApply(Apply apply) {
        for (Apply applyTemp : applies) {
            if (applyTemp.getPlayerName().equalsIgnoreCase(apply.getPlayerName())) {
                return;
            }
        }
        applies.add(apply);
    }

    public void removeApply(Apply apply) {
        if (applies.contains(apply)) {
            applies.remove(apply);
        }
    }

    public void addMoney(double money) {
        if (money >= 0) {
            this.money += money;
        }
    }

    public void takeMoney(double money) {
        if (money >= 0) {
            this.money -= money;
        }
    }

    @SuppressWarnings("unchecked")
    public static Guild deserialize(Map<String, Object> map) {
        Guild guild = new Guild();
        guild.setName((String) map.get("owner"));
        guild.setName((String) map.get("name"));
        guild.setDescription((String) map.get("description"));
        guild.setLevel((int) map.get("level"));
        guild.setMaxPeople((int) map.get("maxPeople"));
        guild.setMoney((double) map.get("money"));
        guild.setCreateTime(new Date((long) map.get("createTime")));
        guild.setMembers((List<String>) map.get("members"));
        guild.setApplies((List<Apply>) map.get("applies"));

        return guild;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("owner", this.owner);
        map.put("name", this.name);
        map.put("description", this.description);
        map.put("level", this.level);
        map.put("maxPeople", this.maxPeople);
        map.put("money", this.money);
        map.put("createTime", this.createTime.getTime());
        map.put("members", this.members);
        map.put("applies", this.applies);
        return map;
    }

}

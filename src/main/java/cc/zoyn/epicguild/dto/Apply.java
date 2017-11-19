package cc.zoyn.epicguild.dto;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.Date;
import java.util.Map;

/**
 * 表示一个公会申请
 *
 * @author Zoyn
 * @since 2017-11-13
 */
@Data
@AllArgsConstructor
public final class Apply implements ConfigurationSerializable {

    private String playerName;
    private Date createTime;

    public static Apply createApply(String playerName, Date createTime) {
        return new Apply(playerName, createTime);
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = Maps.newHashMap();

        map.put("PlayerName", this.playerName);
        map.put("CreateTime", this.createTime.getTime());
        return map;
    }


}

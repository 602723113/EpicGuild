package cc.zoyn.epicguild.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * 表示一个公会申请
 *
 * @author Zoyn
 * @since 2017-11-13
 */
@Data
@AllArgsConstructor
public class Apply {

    private String playerName;
    private Guild applyGuild;
    private Date createTime;

}

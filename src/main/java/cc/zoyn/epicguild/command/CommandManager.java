package cc.zoyn.epicguild.command;

import cc.zoyn.epicguild.util.SubCommand;
import org.bukkit.command.CommandExecutor;

/**
 * 用于规范{@link CommandManagerImpl}
 *
 * @author Zoyn
 * @since 2017-11-12
 */
public interface CommandManager extends CommandExecutor {

    /**
     * 注册一个子命令
     *
     * @param commandName 子命令名
     * @param subCommand  命令执行类
     */
    void registerCommand(String commandName, SubCommand subCommand);

    /**
     * 取消注册一个子命令
     *
     * @param commandName 子命令名
     */
    void unregisterCommand(String commandName);

}

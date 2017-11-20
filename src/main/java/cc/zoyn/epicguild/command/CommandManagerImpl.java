package cc.zoyn.epicguild.command;

import cc.zoyn.epicguild.command.subcommand.*;
import cc.zoyn.epicguild.manager.ConfigManager;
import cc.zoyn.epicguild.util.SubCommand;
import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.Map;

/**
 * 指令管理器
 *
 * @author Zoyn
 * @since 2017-11-12
 */
public class CommandManagerImpl implements CommandExecutor, CommandManager {

    private static Map<String, SubCommand> commandMap = Maps.newHashMap();

    /**
     * 初始化所有子命令
     */
    public CommandManagerImpl() {
        registerCommand("create", new CreateCommand());
        registerCommand("list", new ListCommand());
        registerCommand("reload", new ReloadCommand());
        registerCommand("send", new SendCommand());
        registerCommand("join", new JoinCommand());
    }

    @Override
    public void registerCommand(String commandName, SubCommand subCommand) {
        if (commandMap.containsKey(commandName)) {
            Bukkit.getLogger().warning("duplicate add command!");
        }
        commandMap.put(commandName, subCommand);
    }

    @Override
    public void unregisterCommand(String commandName) {
        if (commandMap.containsKey(commandName)) {
            commandMap.remove(commandName);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            ConfigManager.getStringListByDefault("CommandMessage.Help", Collections.singletonList("&c帮助丢失!"), true)
                    .forEach(sender::sendMessage);
            return true;
        }
        if (!commandMap.containsKey(args[0])) {
            sender.sendMessage(ConfigManager.getStringByDefault("CommandMessage.UnknownCommand", "&c未知命令!", true));
            return true;
        }
        // args[0] ---> SubCommand
        SubCommand subCommand = commandMap.get(args[0]);
        subCommand.execute(sender, args);
        return true;
    }

}

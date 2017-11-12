package cc.zoyn.epicguild.command;

import cc.zoyn.epicguild.command.subcommand.CreateCommand;
import cc.zoyn.epicguild.util.SubCommand;
import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Map;

/**
 * @author Zoyn
 * @since 2017-11-12
 */
public class CommandManager implements CommandExecutor, ICommandManager {

    private static Map<String, SubCommand> commandMap = Maps.newHashMap();

    /**
     * 初始化所有子命令
     */
    public CommandManager() {
        registerCommand("create", new CreateCommand());
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
            return true;
        }
        if (!commandMap.containsKey(args[0])) {
            sender.sendMessage("§c未知命令!");
            return true;
        }
        // args[0] ---> SubCommand
        SubCommand subCommand = commandMap.get(args[0]);
        subCommand.execute(sender, args);
        return true;
    }


}

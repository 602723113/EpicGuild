package cc.zoyn.epicguild.command;

import cc.zoyn.epicguild.util.SubCommand;

/**
 * @author Zoyn
 * @since 2017-11-12
 */
public interface ICommandManager {

    void registerCommand(String commandName, SubCommand subCommand);

    void unregisterCommand(String commandName);

}

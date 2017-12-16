package cc.zoyn.epicguild.manager;

import cc.zoyn.epicguild.gui.GUI;
import org.bukkit.inventory.Inventory;

import java.util.List;

/**
 * @author Zoyn
 * @since 2017-12-16
 */
public interface GUIManager {

    GUI createGui(Inventory inventory);

    GUIManager addGui(GUI gui);

    GUIManager removeGui(GUI gui);

    GUI getGuiByTitle(String guiTitle);

    List<GUI> getGuis();

}

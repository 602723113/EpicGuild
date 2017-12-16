package cc.zoyn.epicguild.manager;

import cc.zoyn.epicguild.gui.GUI;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.Validate;
import org.bukkit.inventory.Inventory;

import java.util.List;

/**
 * @author Zoyn
 * @since 2017-12-16
 */
public class GUIManagerImpl implements GUIManager {

    private static GUIManager instance = new GUIManagerImpl();
    private static final List<GUI> guiList = Lists.newArrayList();

    @Override
    public GUI createGui(Inventory inventory) {
        return new GUI(inventory);
    }

    @Override
    public GUIManager addGui(GUI gui) {
        guiList.add(Validate.notNull(gui));
        return this;
    }

    @Override
    public GUIManager removeGui(GUI gui) {
        guiList.remove(Validate.notNull(gui));
        return this;
    }

    @Override
    public GUI getGuiByTitle(String guiTitle) {
        return null;
    }

    @Override
    public List<GUI> getGuis() {
        return guiList;
    }
}

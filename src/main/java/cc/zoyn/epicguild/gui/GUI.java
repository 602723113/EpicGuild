package cc.zoyn.epicguild.gui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Represent a gui
 *
 * @author Zoyn
 * @since 2017-12-16
 */
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class GUI {

    private Inventory inventory;

    public String getTitle() {
        return inventory.getTitle();
    }

    public void addSlot() {

    }

    public void open(Player player) {
        Validate.notNull(player);

        player.closeInventory();
        player.openInventory(inventory);
    }

}

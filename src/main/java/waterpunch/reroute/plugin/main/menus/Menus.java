package waterpunch.reroute.plugin.main.menus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import waterpunch.reroute.plugin.main.Reroute;

public class Menus {

     static Inventory setBorder(Inventory inv) {
          ItemStack cash = null;
          for (int i = 0; i < 54; ++i) {
               if (i > 8 && i < 45) continue;
               cash = Items.getBlack();
               if (i == 45) cash = Items.getBack();
               inv.setItem(i, new ItemStack(cash));
          }
          return inv;
     }

     public static Inventory getCreateInventory(Player player, Reroute reroute) {
          Inventory Reroute_Inv = Bukkit.createInventory(player, 9 * 6, "REROUTE_CREATE");

          return Reroute_Inv;
     }
}

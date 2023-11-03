package waterpunch.reroute.plugin.main.tool;

import org.bukkit.ChatColor;

public class CollarMessage {

     public static String setRace() {
          return ChatColor.GOLD + "[" + ChatColor.RED + "RERO" + ChatColor.GOLD + "]" + ChatColor.WHITE;
     }

     public static String setInfo() {
          return ChatColor.GOLD + "[" + ChatColor.BLUE + "INFO" + ChatColor.GOLD + "]" + ChatColor.WHITE;
     }

     public static String setWarning() {
          return ChatColor.GOLD + "[" + ChatColor.RED + "WARN" + ChatColor.GOLD + "]" + ChatColor.WHITE;
     }

     public static String setNotPermission() {
          return setWarning() + "このコマンドは統合版専用です";
     }

     public static String setNotFound() {
          return setInfo() + "this ReRoute is Not Found";
     }
}

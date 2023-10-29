package waterpunch.reroute.plugin.main;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import waterpunch.reroute.plugin.main.tool.CollarMessage;

public class Core extends JavaPlugin {

     public static ArrayList<Reroute> Reroutes = new ArrayList<>();

     @Override
     public void onEnable() {}

     @Override
     public void onDisable() {
          System.out.println("ATAMAMOZI-D ENGINE STOP");
     }

     @Override
     public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
          Player player = (Player) sender;
          if (!(sender instanceof Player)) return false;
          if (args.length == 0) {
               onHelp(player);
               return true;
          }
          Reroute reroute = null;
          switch (args[0]) {
               case "help":
                    onHelp(player);
                    break;
               case "list":
                    onList(player);
                    break;
               case "view":
                    if (args.length == 1) {
                         player.sendMessage(CollarMessage.setWarning() + "Need Name");
                         return false;
                    }
                    onView((Player) sender, args[1]);

                    break;
               case "create":
                    if (!player.isOp()) {
                         player.sendMessage(CollarMessage.setNotPermission());
                         return false;
                    }
                    if (args.length == 1) {
                         player.sendMessage(CollarMessage.setWarning() + "Need Name");
                         return false;
                    }
                    onCreate((Player) sender, args[1]);
                    break;
               case "run":
                    if (args.length == 1) {
                         player.sendMessage(CollarMessage.setWarning() + "Need Name");
                         return false;
                    }
                    run(player, args[1]);
                    break;
               case "addcommond":
                    if (!player.isOp()) {
                         player.sendMessage(CollarMessage.setNotPermission());
                         return false;
                    }
                    if (args.length == 1) {
                         player.sendMessage(CollarMessage.setWarning() + "Need Name");
                         return false;
                    }
                    reroute = getReroute(args[1]);
                    if (reroute == null) {
                         CollarMessage.setNotFound();
                         return false;
                    }
                    String com = "";
                    for (int i = 2; i < args.length; i++) com = com + " " + args[i];

                    player.sendMessage(reroute.getName() + " add " + com);
                    reroute.addCommong(com);
                    break;
          }
          return false;
     }

     Reroute getReroute(String Name) {
          for (Reroute re : Reroutes) if (re.getName().equals(Name)) return re;
          return null;
     }

     void onHelp(Player player) {
          player.sendMessage("----------------------");

          player.sendMessage("----------------------");
     }

     void onCreate(Player player, String name) {
          if (!Reroutes.isEmpty()) for (Reroute r : Reroutes) if (r.getName().equals(name)) {
               player.sendMessage(CollarMessage.setInfo() + "Array Name");
               return;
          }
          Reroute reroute = new Reroute(name);
          Reroutes.add(reroute);
          player.sendMessage("Create Reroute !");
     }

     void onItemEdit(Player player, String name) {}

     void onCommondedit(Player player, String name) {}

     void onList(Player player) {
          if (Reroutes.size() == 0) return;
          for (Reroute r : Reroutes) player.sendMessage(r.getName());
     }

     void onView(Player player, String name) {
          Reroute reroute = getReroute(name);
          if (reroute == null) return;
          player.sendMessage(ChatColor.RED + name);
          player.sendMessage(ChatColor.GREEN + "[ COMMOND ]");
          for (String st : reroute.getCommonds()) player.sendMessage(ChatColor.GREEN + " - " + ChatColor.WHITE + st.replaceAll("%P%", player.getName()));
     }

     void run(Player player, String name) {
          Reroute reroute = getReroute(name);
          if (reroute == null) return;
          for (String st : reroute.getCommonds()) Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), st.replaceAll("%P%", player.getName()).trim());
     }
}

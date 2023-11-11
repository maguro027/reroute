package waterpunch.reroute.plugin.main;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import waterpunch.reroute.plugin.main.tool.CollarMessage;
import waterpunch.reroute.plugin.main.tool.CreateJson;

public class Core extends JavaPlugin {

     public static final File file_Race = new File(new File("").getAbsolutePath().toString() + "/plugins/Reroute");
     public static ArrayList<Reroute> Reroutes = new ArrayList<>();

     @Override
     public void onEnable() {
          System.out.println("Command ReRoute System");
          getReroutes();
          /*
             ■■■■■  ■■■■■■    ■■■■ 
            ■■   ■   ■    ■  ■   ■ 
           ■         ■    ■  ■■    
           ■         ■   ■    ■■   
           ■         ■■■■      ■■■ 
           ■         ■   ■       ■■
           ■■        ■   ■■       ■
            ■     ■  ■    ■  ■   ■ 
             ■■■■■  ■■■   ■■ ■■■■■        
           */

     }

     @Override
     public void onDisable() {}

     public static void createfile(String string) {
          try {
               Files.createFile(Paths.get(string));
          } catch (IOException e) {}
     }

     public static void getReroutes() {
          File[] files = CreateJson.file_Race.listFiles();
          if (files == null) return;
          for (File tmpFile : files) if (tmpFile.isDirectory()) {
               getReroutes();
          } else {
               if (tmpFile.getName().substring(tmpFile.getName().lastIndexOf(".")).equals(".json")) {
                    try (FileReader fileReader = new FileReader(tmpFile)) {
                         Gson gson = new Gson();
                         Reroute r = gson.fromJson(fileReader, Reroute.class);
                         if (r.getUUID() == null) {
                              r.setUUID();
                              CreateJson.save(r);
                         }
                         Reroutes.add(r);
                    } catch (JsonSyntaxException | JsonIOException | IOException e) {
                         break;
                    }
               }
          }
     }

     @Override
     public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
          if (args.length == 0) {
               onHelp(sender);
               return true;
          }
          Reroute reroute = null;
          switch (args[0]) {
               case "help":
                    onHelp(sender);
                    break;
               case "list":
                    onList(sender);
                    break;
               case "view":
                    if (args.length == 1) {
                         sender.sendMessage(CollarMessage.setWarning() + "Need Name");
                         return false;
                    }
                    onView((Player) sender, args[1]);

                    break;
               case "create":
                    if (sender instanceof Player) if (!((Player) sender).isOp()) {
                         sender.sendMessage(CollarMessage.setNotPermission());
                         return false;
                    }
                    if (args.length == 1) {
                         sender.sendMessage(CollarMessage.setWarning() + "Need Name");
                         return false;
                    }
                    onCreate((Player) sender, args[1]);
                    break;
               case "addcommand":
                    if (sender instanceof Player) if (!((Player) sender).isOp()) {
                         sender.sendMessage(CollarMessage.setNotPermission());
                         return false;
                    }
                    if (args.length == 1) {
                         sender.sendMessage(CollarMessage.setWarning() + "Need Name");
                         return false;
                    }
                    reroute = getReroute(args[1]);
                    if (reroute == null) {
                         CollarMessage.setNotFound();
                         return false;
                    }
                    String com = "";
                    for (int i = 2; i < args.length; i++) com = com + " " + args[i];

                    sender.sendMessage(reroute.getName() + " add " + com);
                    reroute.addCommong(com);
                    break;
               case "run":
                    if (!(sender instanceof Player)) {
                         sender.sendMessage(CollarMessage.setWarning() + "Sorry this command is Player only!!");
                         return false;
                    }
                    if (args.length == 1) {
                         ((Player) sender).sendMessage(CollarMessage.setWarning() + "Need Name");
                         return false;
                    }
                    run((Player) sender, args[1]);
                    break;
          }
          return false;
     }

     @Override
     public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args) {
          ArrayList<String> subcmd = new ArrayList<String>();

          if (args.length == 1) {
               subcmd.add("create");
               subcmd.add("list");
               subcmd.add("view");
               subcmd.add("addcommand");
               subcmd.add("run");
          }
          return subcmd;
     }

     Reroute getReroute(String Name) {
          for (Reroute re : Reroutes) if (re.getName().equals(Name)) return re;
          return null;
     }

     void onHelp(CommandSender sender) {
          sender.sendMessage("----------------------");
          sender.sendMessage(setHelp("create") + "Create Reroute /reroute create [NAME]");
          sender.sendMessage(setHelp("list") + "View Reroute list");
          sender.sendMessage(setHelp("view") + "View reroute");
          sender.sendMessage(setHelp("addcommand") + "AddCommand /reroute addcommand [NAME] [Command] %P% = Player");
          sender.sendMessage(setHelp("run") + "Run Commands /reroute run [NAME]");
          sender.sendMessage("----------------------");
     }

     String setHelp(String name) {
          return "[ " + ChatColor.GREEN + name + ChatColor.WHITE + " ] ";
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

     void onList(CommandSender sender) {
          if (Reroutes.size() == 0) {
               sender.sendMessage("-- 0 --");
               return;
          }
          for (Reroute r : Reroutes) sender.sendMessage(r.getName());
     }

     void onView(Player player, String name) {
          Reroute reroute = getReroute(name);
          if (reroute == null) {
               player.sendMessage("Unknow Reroute");
               return;
          }
          player.sendMessage(ChatColor.RED + name);
          player.sendMessage(ChatColor.GREEN + "[ COMMOND ]");
          for (String st : reroute.getCommonds()) player.sendMessage(ChatColor.GREEN + " - " + ChatColor.WHITE + st.replaceAll("%P%", ChatColor.GREEN + " [Player] " + ChatColor.WHITE).trim());
     }

     void run(Player player, String name) {
          if (player.getName().indexOf(".") == -1) return;

          player.sendMessage("!test!");
          Reroute reroute = getReroute(name);
          if (reroute == null) {
               player.sendMessage("Unknow Reroute");
               return;
          }
          player.sendMessage("-test-");

          String P = player.getName();
          String N = player.getName();
          N = N.substring(1);

          for (String st : reroute.getCommonds()) {
               st = st.replaceAll("%P%", P);
               st = st.replaceAll("%N%", N);
               st = st.trim();
               Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), st);
          }
     }
}

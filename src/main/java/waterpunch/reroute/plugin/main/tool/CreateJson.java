package waterpunch.reroute.plugin.main.tool;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import waterpunch.reroute.plugin.main.Core;
import waterpunch.reroute.plugin.main.Reroute;

public class CreateJson {

     public static final File file_Race = new File(new File("").getAbsolutePath().toString() + "/plugins/Reroute");

     public static void createfile(String string) {
          try {
               Files.createFile(Paths.get(string));
          } catch (IOException e) {
               e.printStackTrace();
          }
     }

     // public static void saveRace(Player player, Reroute reroute) {
     //      try (Writer writer = new FileWriter(file_Race)) {
     //           Gson gson = new Gson();
     //           gson.toJson(reroute, writer);

     //           player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
     //      } catch (IOException e) {
     //           e.printStackTrace();
     //      }
     // }

     public static void save(Reroute reroute) {
          if (!(file_Race.exists())) file_Race.mkdir();
          String URL = file_Race + "/" + reroute.getName() + ".json";
          Core.createfile(URL);
          try (Writer writer = new FileWriter(URL)) {
               Gson gson = new Gson();
               gson.toJson(reroute, writer);
          } catch (IOException e) {
               e.printStackTrace();
          }
     }
}

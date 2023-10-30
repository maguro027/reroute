package waterpunch.reroute.plugin.main.tool;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class APIConnect {

     public static boolean Connect(String Name) {
          try {
               URL url = new URL("https://api.geysermc.org/v2/xbox/xuid/" + Name);

               HttpURLConnection connection = (HttpURLConnection) url.openConnection();
               connection.setRequestMethod("GET");
               connection.connect();

               InputStream inputStream = connection.getInputStream();
               InputStreamReader reader = new InputStreamReader(inputStream);
               Scanner scanner = new Scanner(reader);
               String response = scanner.nextLine();
               Gson gson = new Gson();
               JsonObject json = gson.fromJson(response, JsonObject.class);

               if (json.get("xuid").getAsString() == null) return false;
               return true;
          } catch (IOException e) {
               return false;
          }
     }
}

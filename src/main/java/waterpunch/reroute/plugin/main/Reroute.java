package waterpunch.reroute.plugin.main;

import java.util.ArrayList;
import java.util.UUID;
import waterpunch.reroute.plugin.main.tool.CreateJson;

public class Reroute {

     private String Name;
     private UUID ID;
     private ArrayList<String> Commonds = new ArrayList<>();

     public Reroute(String name) {
          this.Name = name;
          this.ID = UUID.randomUUID();
          CreateJson.save(this);
     }

     public String getName() {
          return Name;
     }

     public void setName(String name) {
          this.Name = name;
     }

     public UUID getUUID() {
          return ID;
     }

     public void setUUID() {
          this.ID = UUID.randomUUID();
     }

     public void addCommong(String com) {
          Commonds.add(com);
          CreateJson.save(this);
     }

     public ArrayList<String> getCommonds() {
          return this.Commonds;
     }

     public void removeCommong(String com) {
          Commonds.remove(com);
     }

     public void removeCommong(int i) {
          Commonds.remove(i);
     }
}

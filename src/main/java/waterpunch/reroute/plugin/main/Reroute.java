package waterpunch.reroute.plugin.main;

import java.util.ArrayList;
import java.util.UUID;

public class Reroute {

     private String Name;
     private UUID ID;
     private ArrayList<String> Commonds = new ArrayList<>();

     // private ArrayList<ItemStack> Items = new ArrayList<>();

     public Reroute(String name) {
          this.Name = name;
          this.ID = UUID.randomUUID();
     }

     public String getName() {
          return Name;
     }

     public void setName(String name) {
          this.Name = name;
     }

     public UUID getID() {
          return ID;
     }

     public void addCommong(String com) {
          Commonds.add(com);
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

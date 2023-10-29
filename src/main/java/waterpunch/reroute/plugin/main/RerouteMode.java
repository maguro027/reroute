package waterpunch.reroute.plugin.main;

public enum RerouteMode {
     ALWAYS(0),
     ONE_DAY(1);

     @SuppressWarnings("unused")
     private int id;

     private RerouteMode(int id) {
          this.id = id;
     }
}

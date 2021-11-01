import java.io.IOException;

public class app {
  public static void main(String[] args) {
    try {
      Server server = new Server();
      server.Server();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

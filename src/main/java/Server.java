
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Server {
  List<String> historyChat;

  public void Server() throws IOException {
    this.historyChat = new ArrayList<>();

    ServerSocket miChat = new ServerSocket(Integer.parseInt(System.getenv().get("PORT")));
   System.out.println(Integer.parseInt(System.getenv().get("PORT")));
    Socket cliente = null;
    boolean serverIsRunning = true;

    while (serverIsRunning) {
      cliente = miChat.accept();
      System.out.println("conectado:" + miChat.getInetAddress().getHostAddress());
      DataInputStream inputStream = new DataInputStream(cliente.getInputStream());
      DataOutputStream outputStream = new DataOutputStream(cliente.getOutputStream());
      String linea = "";
      /*actualizar estado del chat al entrar a la aplicacion*/
      outputStream.writeUTF(chatState());
      System.out.println("Recibiendo mensaje...");
      linea = inputStream.readUTF();
     SimpleDateFormat format = new SimpleDateFormat("hh:mm");
     Date fecha = new Date();
      historyChat.add( linea+". "+format.format(fecha));
      System.out.println(historyChat);
      String state = "";
  for (String mensaje : historyChat ) {
      state += mensaje+";";
  }
      outputStream.writeUTF(state);

      System.out.println("enviado..");
    }
  }

  public String chatState() {
    String state = "";
    this.historyChat.forEach(
        mensaje -> {
          state.concat(mensaje + ";");
        });
    System.out.println(state);
    return state;
  }
}

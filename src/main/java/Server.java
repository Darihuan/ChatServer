import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Server {
  List<String> historyChat;

  public void Server() throws IOException {
    historyChat = new ArrayList<>();
    ServerSocket miChat = new ServerSocket(8080);
    Socket cliente = null;
    System.out.println(miChat.getInetAddress().getHostAddress());

      cliente = miChat.accept();
      System.out.println("conectado");

      DataInputStream inputStream = new DataInputStream(cliente.getInputStream());
      DataOutputStream outputStream = new DataOutputStream(cliente.getOutputStream());
      String linea = "";
      /*actualizar estado del chat al entrar a la aplicacion*/

      while (cliente.isConnected()) {
        if (linea.equals("actualizar"))
          historyChat.forEach(
              mensaje -> {
                try {
                  outputStream.writeUTF(mensaje);
                } catch (IOException e) {
                  e.printStackTrace();
                }
              });
        else if (!(linea = inputStream.readUTF()).equals("")) {

          outputStream.writeUTF(
              cliente.getInetAddress().getHostAddress() + ":" + new Date() + ": " + linea);
          historyChat.add(
              cliente.getInetAddress().getHostAddress() + ":" + new Date() + ": " + linea);
        }

      System.out.println("desconectado");
      cliente.close();
      inputStream.close();
      outputStream.close();
    }
  }
}

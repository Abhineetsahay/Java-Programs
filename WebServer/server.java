import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
  public void run() throws IOException {
    int port = 8010;
    try {
      ServerSocket socket = new ServerSocket(port);
      socket.setSoTimeout(100000);
      while (true) {
        System.out.println("Sever is listening on port" + port);
        Socket acceptedConnection = socket.accept();
        System.out.println("Conection established" + acceptedConnection.getRemoteSocketAddress());
        try (PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream());
            BufferedReader fromClient = new BufferedReader(
                new InputStreamReader(acceptedConnection.getInputStream()))) {
          toClient.println("Hello from Server");
        }
        socket.close();
      }
    } catch (IOException ex) {
    }

  }

  public static void main(String[] args) {
    server s1 = new server();
    try {
      s1.run();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
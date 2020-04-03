package ServerSocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Controller {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectInputStream in;

    public void run() throws IOException {
        try {
            serverSocket = new ServerSocket(4321);
            System.out.println("Cокет-сервер запущен. Ожидание подключения клиентов...");

            while (true) {
                try {
                    StringBuilder yourData = new StringBuilder();
                    clientSocket = serverSocket.accept();
                    while (clientSocket.getInputStream().read() != -1) {
                        new BufferedReader(new InputStreamReader(clientSocket.getInputStream())).lines().forEach(yourData::append);
                        System.out.println(yourData.toString());
                    }
                    System.out.println("Клиент ушел");
                } catch (NullPointerException ex) {
                    System.out.println(in);
                } finally {
                    clientSocket.close();
                    if (in != null) { in.close(); }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            System.out.println("Сокет-сервер закрыт.");
            serverSocket.close();
        }
    }

    Socket getClientSocket() {
        return clientSocket;
    }
}

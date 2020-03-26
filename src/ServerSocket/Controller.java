package ServerSocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Controller {

    public void run() throws IOException {
        ServerSocket serverSocket = new ServerSocket(4321);
        System.out.println("Сервер-сокет начал слушать порт...");

        while (true) {
            Socket clientSocket = null;

            try {
                clientSocket = serverSocket.accept();
                System.out.println("Новый клиент присоединился: " + clientSocket );

                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

                Thread thread = new ClientHandler(clientSocket, in, out);
                thread.start();

            } catch (IOException ex) {
                if (ex.getMessage().equals("Connection reset")) {
                    System.out.println("Потеряно соединение с клиентом." + clientSocket);
                }
            }
        }
    }
}

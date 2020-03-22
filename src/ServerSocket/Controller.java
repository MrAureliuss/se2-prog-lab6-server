package ServerSocket;

import java.io.IOException;
import java.io.ObjectInputStream;
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
            clientSocket = serverSocket.accept();

            Sender sender = new Sender(this);

            try {
                in = new ObjectInputStream(clientSocket.getInputStream());
                sender.sendString(in.readLine());
            } finally {
                clientSocket.close();
                in.close();
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

package ServerSocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Controller {
    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; // серверсокет
    private static ObjectInputStream in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public void run() throws IOException {
        try {
            try {
                server = new ServerSocket(4322);
                System.out.println("Сервер запущен!");
                while (true) {
                    clientSocket = server.accept();
                    System.out.println("А я все думал, когда же ты появишься: " + clientSocket);
                    try {
                        for (;;) {
                            in = new ObjectInputStream(clientSocket.getInputStream());
                            Object o = (Object) in.readObject();
                            System.out.println(o.getClass().getName());
                        }

                    } catch (EOFException ex) {
                        System.out.println("Клиент " + clientSocket + " того, откинулся...");
                    } finally { // в любом случае сокет будет закрыт
                        clientSocket.close();
                        in.close();
                    }
                }
            } finally {
                System.out.println("Сервер закрыт!");
                server.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e);
        }
    }
}

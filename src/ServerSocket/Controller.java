package ServerSocket;

import Utils.CommandHandler.Decrypting;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Controller {
    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; // серверсокет
    private static ObjectInputStream in; // поток чтения из сокета

    public void run() throws IOException {
        try {
            try {
                server = new ServerSocket(4322);
                System.out.println("Сервер запущен!");
                while (true) {
                    clientSocket = server.accept();
                    System.out.println("А я все думал, когда же ты появишься: " + clientSocket);
                    try {
                        while (true) {
                            in = new ObjectInputStream(clientSocket.getInputStream());
                            Decrypting decrypting = new Decrypting(clientSocket);
                            Object o = in.readObject();
                            decrypting.decrypt(o);
                        }

                    } catch (EOFException ex) {
                        System.out.println("Клиент " + clientSocket + " того, откинулся...");
                    } finally { // в любом случае сокет будет закрыт
                        clientSocket.close();
                        if (in != null) { in.close(); }
                    }
                }
            } finally {
                System.out.println("Сервер закрыт!");
                server.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println(e);
        }
    }
}

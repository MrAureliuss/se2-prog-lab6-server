package ServerSocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

class Zalupa implements Serializable {
    public int size;

    public Zalupa(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    private void readObject(ObjectInputStream test) throws
            IOException, ClassNotFoundException
    {
        // Use of defaultReadObject() :
        test.defaultReadObject();
    }
}

public class Controller {
    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; // серверсокет
    private static ObjectInputStream in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public void run() throws IOException {
        try {
            try {
                server = new ServerSocket(4321);
                System.out.println("Сервер запущен!"); // хорошо бы серверу
                clientSocket = server.accept();
                try {
                    in = new ObjectInputStream(clientSocket.getInputStream());
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    Zalupa zalupa = (Zalupa) in.readObject();
                    System.out.println("sdadad");
                    //out.write("Привет, это Сервер! Подтверждаю, вы написали : " + word + "\n");
                    //out.flush(); // выталкиваем все из буфера
                } finally { // в любом случае сокет будет закрыт
                    clientSocket.close();
                    in.close();
                    out.close();
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

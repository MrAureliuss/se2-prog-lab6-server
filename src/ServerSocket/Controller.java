package ServerSocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class Controller {
    private Reader reader;
    private Writer writer;

    public void run() throws IOException {
        ServerSocket serverSocket = new ServerSocket(4321);
        System.out.println("Сервер-сокет начал слушать порт...");

        while (true) {
            Socket clientSocket = null;

            try {
                clientSocket = serverSocket.accept();
                System.out.println("Новый клиент присоединился: " + clientSocket );

                reader = new InputStreamReader(clientSocket.getInputStream());
                writer = new OutputStreamWriter(clientSocket.getOutputStream());

                writeln("Hi, " + read());
            } catch (IOException ex) {
                if (ex.getMessage().equals("Connection reset")) {
                    System.out.println("Потеряно соединение с клиентом: " + clientSocket);
                }
            }
        }
    }

    public String read () {
        String result = "";
        try {
            char[]chars = new char[100];
            int charsRead = reader.read(chars);
            if (charsRead != -1) {
                result = new String(chars);
            } else {
                result = "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void write(String message) {
        try {
            writer.write(message);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeln(String message) {
        write(message + "\n");
    }
}

package ServerSocket;

import Collection.CollectionManager;
import Utils.CommandHandler.Decrypting;
import Utils.JSON.ParserJson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Controller {
    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; // серверсокет
    private static ObjectInputStream in; // поток чтения из сокета
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    public void run() throws IOException {
        try {
            try {
                ParserJson.fromJsonToCollection();
                logger.info("Создана коллекция для работы");
                server = new ServerSocket(4322);
                logger.info("Сервер запущен!");
                while (true) {
                    clientSocket = server.accept();
                    logger.info("А я все думал, когда же ты появишься: " + clientSocket);
                    try {
                        while (true) {
                            in = new ObjectInputStream(clientSocket.getInputStream());
                            Decrypting decrypting = new Decrypting(clientSocket);
                            Object o = in.readObject();
                            decrypting.decrypt(o);
                        }

                    } catch (EOFException ex) {
                        logger.info("Клиент " + clientSocket + " того, откинулся...");
                    } finally {
                        clientSocket.close();
                        if (in != null) { in.close(); }
                    }
                }
            } finally {
                logger.info("Сервер закрыт!");
                server.close();
            }
        } catch (IOException | ClassNotFoundException | NullPointerException e) {
            e.printStackTrace();
            logger.error(String.valueOf(e));
        }
    }
}

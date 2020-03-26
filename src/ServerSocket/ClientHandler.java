package ServerSocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {
    final Socket socket;
    final ObjectInputStream in;
    final ObjectOutputStream out;

    public ClientHandler(Socket socket, ObjectInputStream in, ObjectOutputStream out) {
        this.socket = socket;
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
        String received;
        String toReturn;

        while (true) {
            try {
                out.writeUTF("ABC");
                received = in.readUTF();

                if (received.equals("exit")) { break; }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            this.out.close();
            this.in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

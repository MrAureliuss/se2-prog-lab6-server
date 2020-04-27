package ServerSocket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;

class Sender {
    Sender(Controller controller) throws IOException {

    }

    void sendString(String message) {
        try {
            outObject.writeUTF(message);
            outString.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    void sendObject(Object object) {
        try {
            outObject.writeObject(object);
            outObject.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}


/*
package ServerSocket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;

class Sender {
    private ObjectOutputStream outObject;
    private BufferedWriter outString;

    Sender(Controller controller) throws IOException {
        outObject = new ObjectOutputStream(controller.getClientSocket().getOutputStream());
        outString = new BufferedWriter(new OutputStreamWriter(controller.getClientSocket().getOutputStream()));
    }

    void sendString(String message) {
        try {
            outString.write(message);
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
*/

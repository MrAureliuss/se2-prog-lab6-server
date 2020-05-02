import ServerSocket.Controller;
import Utils.JSON.ParserJson;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().addShutdownHook(new Thread(ParserJson::collectionToJson));
        Controller controller = new Controller();
        controller.run(args[0]);
    }
}

package Utils.CommandHandler;

import Commands.Command;
import Commands.SerializedCommands.SerializedSimplyCommand;

import java.io.IOException;
import java.net.Socket;

public class Decrypting {
    private final Socket socket;

    public Decrypting(Socket socket) {
        this.socket = socket;
    }

    public void decrypt(Object o) throws IOException {
        if (o instanceof SerializedSimplyCommand) {
            SerializedSimplyCommand simplyCommand = (SerializedSimplyCommand) o;
            Command command = simplyCommand.getCommand();
            String[] args = {};
            command.execute(args, socket);
        }
    }
}

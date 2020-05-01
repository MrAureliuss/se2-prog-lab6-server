package Utils.CommandHandler;

import Commands.Command;
import Commands.SerializedCommands.SerializedArgumentCommand;
import Commands.SerializedCommands.SerializedCombinedCommand;
import Commands.SerializedCommands.SerializedObjectCommand;
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
            String arg = "";
            command.execute(arg, socket);
        }

        if (o instanceof SerializedArgumentCommand) {
            SerializedArgumentCommand argumentCommand = (SerializedArgumentCommand) o;
            Command command = argumentCommand.getCommand();
            String arg = argumentCommand.getArg();
            command.execute(arg, socket);
        }

        if (o instanceof SerializedObjectCommand) {
            SerializedObjectCommand objectCommand = (SerializedObjectCommand) o;
            Command command = objectCommand.getCommand();
            Object arg = objectCommand.getObject();
            command.execute(arg, socket);
        }

        if (o instanceof SerializedCombinedCommand) {
            SerializedCombinedCommand combinedCommand = (SerializedCombinedCommand) o;
            Command command = combinedCommand.getCommand();
            command.execute(combinedCommand, socket);
        }
    }
}

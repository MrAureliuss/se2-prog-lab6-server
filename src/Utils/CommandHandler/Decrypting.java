package Utils.CommandHandler;

import Commands.Command;
import Commands.SerializedCommands.SerializedSimplyCommand;

import java.io.IOException;

public class Decrypting {
    public static void decrypt(Object o) throws IOException {
        if (o instanceof SerializedSimplyCommand) {
            SerializedSimplyCommand simplyCommand = (SerializedSimplyCommand) o;
            Command command = simplyCommand.getCommand();
            String[] args = {};
            command.execute(args);
        }
    }
}

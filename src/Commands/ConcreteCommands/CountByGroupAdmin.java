package Commands.ConcreteCommands;

import BasicClasses.Person;
import Commands.Command;
import Commands.CommandReceiver;

import java.io.IOException;
import java.net.Socket;

/**
 * Конкретная команда подсчета по админу.
 */
public class CountByGroupAdmin extends Command {
    private static final long serialVersionUID = 32L;

    @Override
    public void execute(Object argObject, Socket socket) throws IOException {
        CommandReceiver commandReceiver = new CommandReceiver(socket);
        commandReceiver.countByGroupAdmin((Person) argObject);
    }

}

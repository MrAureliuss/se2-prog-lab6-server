package Commands.ConcreteCommands;

import Commands.Command;
import Commands.CommandReceiver;

import java.io.IOException;
import java.net.Socket;

/**
 * Конкретная команда удаления объектов, превышающих заданный.
 */
public class RemoveGreater extends Command {
    private static final long serialVersionUID = 32L;

    @Override
    public void execute(String arg, Socket socket) throws IOException {
        CommandReceiver commandReceiver = new CommandReceiver(socket);
        commandReceiver.removeGreater();
    }
}

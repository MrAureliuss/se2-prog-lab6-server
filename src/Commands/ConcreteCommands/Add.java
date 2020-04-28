package Commands.ConcreteCommands;

import Commands.Command;
import Commands.CommandReceiver;

import java.io.IOException;
import java.net.Socket;

/**
 * Конкретная команда добавления в коллекцию.
 */
public class Add extends Command {
    private static final long serialVersionUID = 32L;

    @Override
    public void execute(String[] args, Socket socket) throws IOException {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде add.");
        }
        CommandReceiver commandReceiver = new CommandReceiver(socket);
        commandReceiver.add();
    }
}

package Commands.ConcreteCommands;

import Commands.Command;
import Commands.CommandReceiver;

import java.io.IOException;
import java.net.Socket;

/**
 * Конкретная команда удаления по ID.
 */
public class RemoveByID extends Command {
    private static final long serialVersionUID = 32L;

    @Override
    public void execute(String[] args, Socket socket) throws IOException {
        if (args.length == 1) {
            CommandReceiver commandReceiver = new CommandReceiver(socket);
            commandReceiver.removeById(args[0]);
        }
        else { System.out.println("Некорректное количество аргументов. Для справки напишите help."); }
    }
}

package Commands.ConcreteCommands;

import Commands.Command;
import Commands.CommandReceiver;

import java.io.IOException;
import java.net.Socket;

/**
 * Конкретная команда обновления объекта.
 */
public class Update extends Command {
    private static final long serialVersionUID = 32L;

    @Override
    public void execute(String arg, Socket socket) throws IOException {
        if (arg.split(" ").length == 1) {
            CommandReceiver commandReceiver = new CommandReceiver(socket);
            commandReceiver.update(arg);
        }
        else { System.out.println("Некорректное количество аргументов. Для справки напишите help."); }
    }
}

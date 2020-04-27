package Commands.ConcreteCommands;

import Commands.Command;
import Commands.CommandReceiver;

import java.io.IOException;

/**
 * Конкретная команда удаления по ID.
 */
public class RemoveByID extends Command {
    private static final long serialVersionUID = 32L;

    @Override
    public void execute(String[] args) throws IOException {
        if (args.length == 2) { CommandReceiver.removeById(args[1]); }
        else { System.out.println("Некорректное количество аргументов. Для справки напишите help."); }
    }
}

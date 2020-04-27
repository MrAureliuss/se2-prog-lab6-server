package Commands.ConcreteCommands;

import Commands.Command;
import Commands.CommandReceiver;

import java.io.IOException;

/**
 * Конкретная команда показа содержания коллекции.
 */
public class Show extends Command {
    private static final long serialVersionUID = 32L;

    @Override
    public void execute(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде show.");
        }
        CommandReceiver.show();
    }
}

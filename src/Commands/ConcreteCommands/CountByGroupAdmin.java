package Commands.ConcreteCommands;

import Commands.Command;
import Commands.CommandReceiver;

import java.io.IOException;

/**
 * Конкретная команда подсчета по админу.
 */
public class CountByGroupAdmin extends Command {
    private static final long serialVersionUID = 32L;

    @Override
    public void execute(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде count_by_group_admin.");
        }
        CommandReceiver.countByGroupAdmin();
    }

}

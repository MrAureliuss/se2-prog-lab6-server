package Commands.ConcreteCommands;

import Commands.Command;
import Commands.CommandReceiver;

/**
 * Конкретная команда подсчета по админу.
 */
public class CountByGroupAdmin extends Command {
    private final CommandReceiver commandReceiver;

    public CountByGroupAdmin(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде count_by_group_admin.");
        }
        commandReceiver.countByGroupAdmin();
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда count_by_group_admin  – вывести количество элементов, значение поля groupAdmin которых равно заданному.");
    }
}

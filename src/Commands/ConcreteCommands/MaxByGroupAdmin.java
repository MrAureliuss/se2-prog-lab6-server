package Commands.ConcreteCommands;

import Commands.Command;
import Commands.CommandReceiver;
/**
 * Конкретная команда подсчета по "максимальному" админу.
 */
public class MaxByGroupAdmin extends Command {
    private final CommandReceiver commandReceiver;

    public MaxByGroupAdmin(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Введен не нужный аргумент. Команда приведена к базовой команде max_by_group_admin.");
        }
        commandReceiver.maxByGroupAdmin();
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда max_by_group_admin – вывести любой объект из коллекции, значение поля groupAdmin которого является максимальным.");
    }
}

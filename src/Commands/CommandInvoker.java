package Commands;

import java.io.IOException;
import java.util.HashMap;

/**
 * Инвокер(вызыватель), выполяет команды. Хранит зарегистрированные команды.
 */
public class CommandInvoker {
    private final HashMap<String, Command> commandMap = new HashMap<>();

    public void register(String commandName, Command command) {
        commandMap.put(commandName, command);
    }

    public void executeCommand(String[] commandName) {
        try {
            if (commandName.length > 0) {
                Command command = commandMap.get(commandName[0]);
                command.execute(commandName);
            } else { System.out.println("Вы не ввели команду."); }
        } catch (IllegalStateException | NullPointerException | IOException ex) {
            ex.printStackTrace();
            System.out.println("Не существует команды " + commandName[0] + ". Для справки используйте – help");
        }
    }

    HashMap<String, Command> getCommandMap() {
        return commandMap;
    }
}

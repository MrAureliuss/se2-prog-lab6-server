package Commands.ConcreteCommands;

import Commands.Command;
import Commands.CommandReceiver;

import java.io.IOException;
import java.io.Serializable;

/**
 * Конкретная команда получения информации о коллекции.
 */
public class Info extends Command implements Serializable {
    private static final long serialVersionUID = 32L;

    @Override
    public void execute(String[] args) throws IOException {
        CommandReceiver.info();
    }
}

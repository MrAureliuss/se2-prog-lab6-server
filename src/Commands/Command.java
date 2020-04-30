package Commands;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

/**
 * Абстрактный класс команд. На его основе создается остальные команды.
 */
public abstract class Command implements Serializable {
    private static final long serialVersionUID = 32L;
    public abstract void execute(Object argObject, Socket socket) throws IOException;
}

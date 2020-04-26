package Commands;

import java.io.IOException;
import java.io.Serializable;

/**
 * Абстрактный класс команд. На его основе создается остальные команды.
 */
public abstract class Command implements Serializable {
    protected abstract void writeInfo();
    protected abstract void execute(String[] args) throws IOException;
}

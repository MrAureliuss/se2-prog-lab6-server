package BasicClasses;

import java.io.Serializable;

public enum Semester implements Serializable {
    THIRD(3),
    FOURTH(4),
    FIFTH(5);

    private final int value;

    Semester(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

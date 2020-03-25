package BasicClasses;

public enum Semester {
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

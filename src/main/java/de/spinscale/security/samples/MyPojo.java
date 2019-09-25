package de.spinscale.security.samples;

public class MyPojo {

    private static final Boolean FOO = Boolean.TRUE;
    private final String value;

    MyPojo(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "value=[" + value + "], FOO=[" + FOO + "]";
    }
}

package nl.tudelft.semgroup4.util;

public class KeyBindingEntry {

    private final String key;
    private int value;

    public KeyBindingEntry(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

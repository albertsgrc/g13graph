package g13;

/**
 * Created by albert on 16/04/15.
 */
public class tNode extends Node {
    private final int key;
    private String value;

    public tNode(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public tNode(int key) {
        this(key, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != getClass()) return false;
        return ((tNode) o).getKey() == this.key;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(key);
    }

    @Override
    public String toString() {
        return Integer.toString(key);
    }

    @Override
    public boolean isGreater(Node n) {
        return ((tNode) n).getKey() < this.key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getKey() {
        return key;
    }
}

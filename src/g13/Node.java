package g13;

/**
 * Created by miquel on 20/03/15.
 */
public abstract class Node {
    // TODO: This methods need to be implemented

    /**
     *
     * @param o
     * @return
     */
    @Override public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Node n = (Node) o;
        return true; // TODO: Finish
    }

    /**
     * Take a look at
     * http://stackoverflow.com/questions/113511/hash-code-implementation
     * for guidelines on implementing this function
     * @return
     */
    @Override public int hashCode() {
        int hash = 3;

        // TODO: Fill

        return hash;
    }

    /**
     *
     * @return
     */
    @Override public String toString() {
        return ""; // TODO: Implement
    }
}
